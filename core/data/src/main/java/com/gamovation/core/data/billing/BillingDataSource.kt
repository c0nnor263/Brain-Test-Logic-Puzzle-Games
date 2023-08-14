package com.gamovation.core.data.billing

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.ProductType
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchaseHistoryParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.consumePurchase
import com.android.billingclient.api.queryProductDetails
import com.android.billingclient.api.queryPurchaseHistory
import com.android.billingclient.api.queryPurchasesAsync
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gamovation.core.data.BuildConfig
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.gamovation.core.domain.billing.UserVipType
import com.gamovation.core.domain.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

@ActivityRetainedScoped
class BillingDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
) : DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i("TAG", "onResume: ")
        if (client.isReady) {
            scope.launch {
                queryProductDetails()
            }
        }
    }

    // Repository Pattern
    private val _productsDetailsFlow = MutableStateFlow(ProductDetailsInfo(null, null))
    val productsDetailsFlow: StateFlow<ProductDetailsInfo> = _productsDetailsFlow.asStateFlow()

    private val _pendingBenefitFlow = MutableStateFlow(PurchaseProduct())
    val pendingBenefitFlow: StateFlow<PurchaseProduct> = _pendingBenefitFlow.asStateFlow()

    private val billingStateListener = object : BillingClientStateListener {
        override fun onBillingSetupFinished(billingResult: BillingResult) {
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                scope.launch(Dispatchers.IO) {
                    queryProductDetails()
                }
            }
        }

        override fun onBillingServiceDisconnected() {
            initClient()
            // TODO Delay
        }
    }
    private val purchasesListener = PurchasesUpdatedListener { billingResult, purchases ->
        Log.i("TAG", "BillingClient: responseCode ${billingResult.responseCode}")


        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                purchases?.forEach { purchase ->
                    Log.i("TAG", "BillingClient: purchase $purchase")
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && purchase.isAcknowledged.not()) {
                        verifyPurchase(purchase)
                    }
                }
            }

            BillingClient.BillingResponseCode.USER_CANCELED -> {
                updatePendingBenefitResult(VerifyResult.CANCELLED)
            }

            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> {
                initClient()
            }

            else -> {}
        }
    }

    private val client =
        BillingClient.newBuilder(context)
            .setListener(purchasesListener)
            .enablePendingPurchases()
            .build()


    fun initClient() {
        client.startConnection(billingStateListener)
    }

    private suspend fun queryPurchases() = withContext(Dispatchers.IO) {
        val inAppParams =
            QueryPurchasesParams.newBuilder().setProductType(ProductType.INAPP).build()
        val subscriptionParams =
            QueryPurchasesParams.newBuilder().setProductType(ProductType.SUBS).build()

        val inAppProductsResult = client.queryPurchasesAsync(inAppParams)
        val subscriptionsResult = client.queryPurchasesAsync(subscriptionParams)

        if (inAppProductsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            inAppProductsResult.purchasesList.forEach { purchase ->
                verifyPurchase(purchase)
            }
        }

        if (subscriptionsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            subscriptionsResult.purchasesList.forEach { purchase ->
                verifyPurchase(purchase)
            }
        }
    }

    suspend fun queryProductDetails() = withContext(Dispatchers.IO) {
        val inAppParams = QueryProductDetailsParams.newBuilder()
            .setProductList(BillingProductType.values()
                .filter { it.productType == ProductType.INAPP }
                .map {
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(it.id)
                        .setProductType(ProductType.INAPP)
                        .build()
                }
            )
            .build()

        val subscriptionParams = QueryProductDetailsParams.newBuilder()
            .setProductList(BillingProductType.values()
                .filter { it.productType == ProductType.SUBS }
                .map {
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(it.id)
                        .setProductType(ProductType.SUBS)
                        .build()
                }
            ).build()

        val inAppDetailsResult = client.queryProductDetails(inAppParams)
        val subscriptionDetailsResult = client.queryProductDetails(subscriptionParams)


        if (inAppDetailsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            val result = inAppDetailsResult.productDetailsList
            _productsDetailsFlow.value = _productsDetailsFlow.value.copy(
                inAppDetails = result
            )
        }
        if (subscriptionDetailsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            val result = subscriptionDetailsResult.productDetailsList
            _productsDetailsFlow.value =
                _productsDetailsFlow.value.copy(subscriptionDetails = result)
        }
    }

    fun purchaseProduct(
        details: ProductDetails,
        type: BillingProductType,
        onRequestActivity: () -> ComponentActivity
    ) {
        Log.i("TAG", "purchaseProduct: $details")
        try {
            val productDetailsParamsList =
                BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(details)
                    .apply {
                        if (type == BillingProductType.VIP) {
                            setOfferToken(
                                details.subscriptionOfferDetails?.getOrNull(0)?.offerToken ?: ""
                            )
                        }
                    }.build()
            val flowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(listOf(productDetailsParamsList)).build()
            _pendingBenefitFlow.value = PurchaseProduct(type, VerifyResult.PENDING)
            client.launchBillingFlow(onRequestActivity(), flowParams)
        } catch (e: Exception) {
            _pendingBenefitFlow.value = PurchaseProduct(type, VerifyResult.FAILED)
        }


    }


    private fun verifyPurchase(purchase: Purchase?) {
        val json = purchase?.originalJson?.let { JSONObject(it) }
        val productId = json?.getString("productId")
        Log.i("TAG", "verifyPurchase: $purchase")
        val url =
            BuildConfig.verifyPurchases + "?" +
                    "purchaseToken=${purchase?.purchaseToken}&" +
                    "productId=$productId"
        Log.i("TAG", "verifyPurchase: $url")

        val responseListener = Response.Listener<String> { response ->
            try {
                Log.i("TAG", "verifyPurchase: response $response")
                val isValid = JSONObject(response).getBoolean("isValid")
                if (isValid) {
                    updatePendingBenefitType(json)
                    when (productId) {
                        BillingProductType.VIP.id,
                        BillingProductType.COOLEST_OFFER.id,
                        BillingProductType.BEST_CHOICE_OFFER.id -> {
                            acknowledgePurchase(purchase)
                        }

                        else -> consumePurchase(purchase)
                    }
                } else updatePendingBenefitResult(VerifyResult.FAILED)
            } catch (e: Exception) {
                Log.i("TAG", "verifyPurchase: error $response")
                updatePendingBenefitResult(VerifyResult.FAILED)
            }

        }

        val request = StringRequest(
            Request.Method.POST, url, responseListener
        ) { error ->
            Log.i(
                "TAG",
                "verifyPurchase: net error ${error.networkResponse.statusCode} \n${error.message}"
            )
            updatePendingBenefitResult(VerifyResult.FAILED)
        }

        Volley.newRequestQueue(context).add(request)
    }


    private fun receiveProduct(type: BillingProductType?) = scope.launch {
        when (type) {
            BillingProductType.COOLEST_OFFER -> {
                userInfoPreferencesRepository.buyCurrency(250)
                userInfoPreferencesRepository.setUserVipType(UserVipType.ADS_FREE)
            }

            BillingProductType.BEST_CHOICE_OFFER -> {
                userInfoPreferencesRepository.buyCurrency(1000)
                userInfoPreferencesRepository.setUserVipType(UserVipType.ADS_FREE)
            }

            BillingProductType.CURRENCY_250 -> {
                userInfoPreferencesRepository.buyCurrency(250)
            }

            BillingProductType.CURRENCY_500 -> {
                userInfoPreferencesRepository.buyCurrency(500)
            }

            BillingProductType.CURRENCY_1000 -> {
                userInfoPreferencesRepository.buyCurrency(1000)
            }

            BillingProductType.REMOVE_ADS -> {
                userInfoPreferencesRepository.setUserVipType(UserVipType.ADS_FREE)
            }

            BillingProductType.VIP -> {
                userInfoPreferencesRepository.setUserVipType(UserVipType.PREMIUM)
            }

            else -> {}
        }
    }

    private fun consumePurchase(purchase: Purchase?) {
        Log.i("TAG", "consumePurchase: $purchase")
        val params =
            ConsumeParams.newBuilder().setPurchaseToken(purchase?.purchaseToken ?: "").build()
        scope.launch {
            val consumeResult = client.consumePurchase(params)
            Log.i("TAG", "consumePurchase: $consumeResult")
            if (consumeResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                updatePendingBenefitResult(VerifyResult.SUCCESS)
            } else {
                updatePendingBenefitResult(VerifyResult.FAILED)
                return@launch
            }
        }
    }


    private fun acknowledgePurchase(purchase: Purchase?) {
        Log.i("TAG", "acknowledgePurchase: $purchase")
        if (purchase?.isAcknowledged == true) return

        val acknowledgeParams =
            AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase?.purchaseToken ?: "")
                .build()
        scope.launch {
            val acknowledgeResult = client.acknowledgePurchase(acknowledgeParams)
            Log.i("TAG", "acknowledgePurchase: $acknowledgeResult")
            if (acknowledgeResult.responseCode == BillingClient.BillingResponseCode.OK) {
                updatePendingBenefitResult(VerifyResult.SUCCESS)
            } else {
                updatePendingBenefitResult(VerifyResult.FAILED)
                return@launch
            }
        }
    }

    private fun updatePendingBenefitResult(result: VerifyResult) {
        _pendingBenefitFlow.value = pendingBenefitFlow.value.copy(result = result)

        if (result == VerifyResult.SUCCESS) {
            receiveProduct(pendingBenefitFlow.value.type)
        }
    }

    private fun updatePendingBenefitType(json: JSONObject?) {
        Log.i("TAG", "updatePendingBenefitType: json $json")
        val type = BillingProductType.values().find { billingProductType ->
            json?.optString("productId") == billingProductType.id
        }
        Log.i("TAG", "updatePendingBenefitType: type $type")
        _pendingBenefitFlow.value = pendingBenefitFlow.value.copy(type = type)
    }

    fun endConnection() {
        client.endConnection()
    }

    fun restorePurchases() = scope.launch {
        val inAppHistoryParams = QueryPurchaseHistoryParams.newBuilder().setProductType(
            ProductType.INAPP
        ).build()

        val subscriptionHistoryParams = QueryPurchaseHistoryParams.newBuilder().setProductType(
            ProductType.SUBS
        ).build()

        val inAppResult = client.queryPurchaseHistory(inAppHistoryParams)
        val subscriptionResult = client.queryPurchaseHistory(subscriptionHistoryParams)
        Log.i("TAG", "getHistoryRecords: ${inAppResult.purchaseHistoryRecordList}")
        Log.i("TAG", "getHistoryRecords: ${subscriptionResult.purchaseHistoryRecordList}")
        if (inAppResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK || subscriptionResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            queryPurchases()
        }
    }


}