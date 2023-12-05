package com.gamovation.core.data.billing

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
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
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

@ActivityRetainedScoped
class BillingDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) {
    companion object {
        const val TAG = "BillingDataSource"
    }

    private val initialBillingStartTime = System.currentTimeMillis()
    var fetchDelay: Long = TimeUnit.SECONDS.toMillis(1)

    // Repository Pattern
    private val _productsDetailsFlow = MutableStateFlow(ProductDetailsInfo(null, null))
    val productsDetailsFlow: StateFlow<ProductDetailsInfo> = _productsDetailsFlow.asStateFlow()

    private val _pendingBenefitFlow = MutableStateFlow(PurchaseProduct())
    val pendingBenefitFlow: StateFlow<PurchaseProduct> = _pendingBenefitFlow.asStateFlow()

    private val billingStateListener = object : BillingClientStateListener {
        override fun onBillingSetupFinished(billingResult: BillingResult) {
            Log.i(TAG, "onBillingSetupFinished: billingResult $billingResult")
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                scope.launch(Dispatchers.IO) {
                    queryProductDetails()
                }
            }
        }

        override fun onBillingServiceDisconnected() {
            Log.i(TAG, "onBillingServiceDisconnected: ")
            scope.launch {
                delay(fetchDelay)
                initClient()

                val timePass = System.currentTimeMillis() - initialBillingStartTime
                if (timePass > TimeUnit.SECONDS.toMillis(30)) {
                    fetchDelay = TimeUnit.SECONDS.toMillis(1)
                } else {
                    fetchDelay += fetchDelay
                }
            }
        }
    }
    private val purchasesListener = PurchasesUpdatedListener { billingResult, purchases ->
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                purchases?.forEach { purchase ->
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED &&
                        purchase.isAcknowledged.not()
                    ) {
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

        val (inAppBillingResult, inAppPurchasesList) = client.queryPurchasesAsync(inAppParams)
        val (subscriptionBillingResult, subscriptionPurchasesList) = client.queryPurchasesAsync(
            subscriptionParams
        )

        if (inAppBillingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            inAppPurchasesList.forEach { purchase ->
                verifyPurchase(purchase)
            }
        }

        if (subscriptionBillingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            subscriptionPurchasesList.forEach { purchase ->
                verifyPurchase(purchase)
            }
        }
    }

    suspend fun queryProductDetails() = withContext(Dispatchers.IO) {
        val inAppParams = QueryProductDetailsParams.newBuilder()
            .setProductList(
                BillingProductType.entries
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
            .setProductList(
                BillingProductType.entries
                    .filter { it.productType == ProductType.SUBS }
                    .map {
                        QueryProductDetailsParams.Product.newBuilder()
                            .setProductId(it.id)
                            .setProductType(ProductType.SUBS)
                            .build()
                    }
            ).build()

        val (inAppBillingResult, inAppDetailsList) = client.queryProductDetails(inAppParams)
        val (subscriptionBillingResult, subscriptionDetailsList) = client.queryProductDetails(
            subscriptionParams
        )

        if (inAppBillingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            _productsDetailsFlow.value = _productsDetailsFlow.value.copy(
                inAppDetails = inAppDetailsList
            )
        }
        if (subscriptionBillingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            _productsDetailsFlow.value =
                _productsDetailsFlow.value.copy(
                    subscriptionDetails = subscriptionDetailsList
                )
        }
    }

    fun purchaseProduct(
        details: ProductDetails,
        type: BillingProductType,
        onRequestActivity: () -> ComponentActivity
    ) {
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
        val purchaseJson = purchase?.originalJson?.let { JSONObject(it) }
        val productId = purchaseJson?.getString("productId")
        val type = when (productId) {
            BillingProductType.VIP.id -> ProductType.SUBS
            else -> ProductType.INAPP
        }
        val url =
            BuildConfig.verifyPurchases + "?" +
                "purchaseToken=${purchase?.purchaseToken}&" +
                "productId=$productId&" +
                "productType=$type"

        val responseListener = Response.Listener<String> { response ->
            val isValid = JSONObject(response).getBoolean("isValid")
            if (isValid) {
                updatePendingBenefitType(purchaseJson)
                when (productId) {
                    BillingProductType.VIP.id,
                    BillingProductType.REMOVE_ADS.id,
                    BillingProductType.SMARTEST_OFFER.id,
                    BillingProductType.BEST_CHOICE_OFFER.id
                    -> {
                        acknowledgePurchase(purchase)
                    }

                    else -> consumePurchase(purchase)
                }
            } else {
                updatePendingBenefitResult(VerifyResult.FAILED)
            }
        }

        val request = StringRequest(
            Request.Method.POST,
            url,
            responseListener
        ) { error ->
            when (productId) {
                BillingProductType.VIP.id,
                BillingProductType.REMOVE_ADS.id,
                BillingProductType.SMARTEST_OFFER.id,
                BillingProductType.BEST_CHOICE_OFFER.id
                -> {
                    updatePendingBenefitResult(VerifyResult.FAILED)
                }

                else -> {
                    updatePendingBenefitType(purchaseJson)
                    consumePurchase(purchase)
                }
            }
        }

        Volley.newRequestQueue(context).add(request)
    }

    private fun receiveProduct(type: BillingProductType?, currencyInclude: Boolean) = scope.launch {
        when (type) {
            BillingProductType.SMARTEST_OFFER -> {
                if (currencyInclude) {
                    userInfoPreferencesRepository.buyCurrency(250)
                }
                userInfoPreferencesRepository.setUserVipType(UserVipType.ADS_FREE)
            }

            BillingProductType.BEST_CHOICE_OFFER -> {
                if (currencyInclude) {
                    userInfoPreferencesRepository.buyCurrency(1000)
                }
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
        val params =
            ConsumeParams.newBuilder()
                .setPurchaseToken(purchase?.purchaseToken ?: "")
                .build()
        scope.launch {
            val consumeResult = client.consumePurchase(params)
            if (consumeResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                updatePendingBenefitResult(VerifyResult.SUCCESS)
            } else {
                updatePendingBenefitResult(VerifyResult.FAILED)
            }
        }
    }

    private fun acknowledgePurchase(purchase: Purchase?) {
        if (purchase?.isAcknowledged == true) {
            updatePendingBenefitResult(VerifyResult.SUCCESS, false)
            return
        }

        val acknowledgeParams =
            AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase?.purchaseToken ?: "")
                .build()
        scope.launch {
            val acknowledgeResult = client.acknowledgePurchase(acknowledgeParams)
            if (acknowledgeResult.responseCode == BillingClient.BillingResponseCode.OK) {
                updatePendingBenefitResult(VerifyResult.SUCCESS)
            } else {
                updatePendingBenefitResult(VerifyResult.FAILED)
            }
        }
    }

    private fun updatePendingBenefitResult(result: VerifyResult, currencyInclude: Boolean = true) {
        _pendingBenefitFlow.value = pendingBenefitFlow.value.copy(result = result)

        if (result == VerifyResult.SUCCESS) {
            receiveProduct(pendingBenefitFlow.value.type, currencyInclude)
        }
    }

    private fun updatePendingBenefitType(json: JSONObject?) {
        val type = BillingProductType.entries.find { billingProductType ->
            json?.optString("productId") == billingProductType.id
        }
        _pendingBenefitFlow.value = pendingBenefitFlow.value.copy(type = type)
    }

    fun endConnection() {
        client.endConnection()
    }

    fun onResumeBilling() {
        if (client.isReady) {
            scope.launch {
                queryProductDetails()
            }
        }
    }

    fun restorePurchases() = scope.launch {
        val inAppHistoryParams = QueryPurchaseHistoryParams.newBuilder().setProductType(
            ProductType.INAPP
        ).build()

        val subscriptionHistoryParams = QueryPurchaseHistoryParams.newBuilder().setProductType(
            ProductType.SUBS
        ).build()

        val (inAppBillingResult, _) = client.queryPurchaseHistory(inAppHistoryParams)
        val (subscriptionBillingResult, _) = client.queryPurchaseHistory(subscriptionHistoryParams)
        if (
            inAppBillingResult.responseCode == BillingClient.BillingResponseCode.OK ||
            subscriptionBillingResult.responseCode == BillingClient.BillingResponseCode.OK
        ) {
            queryPurchases()
        }
    }
}
