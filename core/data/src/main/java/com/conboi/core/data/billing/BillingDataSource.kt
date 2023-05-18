package com.conboi.core.data.billing

import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.ProductType
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import com.conboi.core.domain.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillingDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope
) {
    private val _productsDetailsFlow =
        MutableStateFlow(ProductDetailsInfo(null, null))
    val productsDetailsFlow: StateFlow<ProductDetailsInfo?> = _productsDetailsFlow.asStateFlow()


    private val listener = PurchasesUpdatedListener { billingResult, purchases ->
        // To be implemented in a later section.
    }
    private val client = BillingClient.newBuilder(context)
        .setListener(listener)
        .enablePendingPurchases()
        .build()

    fun initClient() {
        client.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.i("TAG", "onBillingSetupFinished $billingResult")
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {

                    scope.launch(Dispatchers.IO) {
                        querySkuDetails()
                    }
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.i("TAG", "onBillingServiceDisconnected")
                client.startConnection(this)
            }
        })
    }

    suspend fun querySkuDetails() {
        val inAppParams = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.currency250)
                        .setProductType(ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.currency500)
                        .setProductType(ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.currency1000)
                        .setProductType(ProductType.INAPP)
                        .build(),

                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.removeAds)
                        .setProductType(ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.bestOffer)
                        .setProductType(ProductType.INAPP)
                        .build(),
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(InAppProductsIDs.coolestOffer)
                        .setProductType(ProductType.INAPP)
                        .build(),
                )
            )
            .build()

        val subscriptionParams = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(SubscriptionsIDs.vip)
                        .setProductType(ProductType.SUBS)
                        .build(),
                )
            )
            .build()


        val inAppDetailsResult = withContext(Dispatchers.IO) {
            client.queryProductDetails(inAppParams)
        }

        val subscriptionDetailsResult = withContext(Dispatchers.IO) {
            client.queryProductDetails(subscriptionParams)
        }

        if (inAppDetailsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            _productsDetailsFlow.value = _productsDetailsFlow.value.copy(
                inAppDetails = inAppDetailsResult.productDetailsList
            )
        }
        if (subscriptionDetailsResult.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            _productsDetailsFlow.value = _productsDetailsFlow.value.copy(
                subscriptionDetails = subscriptionDetailsResult.productDetailsList
            )
        }

    }


}