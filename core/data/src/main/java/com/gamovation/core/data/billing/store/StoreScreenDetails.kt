package com.gamovation.core.data.billing.store

import com.android.billingclient.api.ProductDetails
import com.gamovation.core.data.billing.BillingProductType
import com.gamovation.core.data.billing.ProductDetailsInfo

data class StoreScreenDetails(
    val bestChoiceDetails: ProductDetails?,
    val smartestOfferDetails: ProductDetails?,
    val currency250Details: ProductDetails?,
    val currency500Details: ProductDetails?,
    val currency1000Details: ProductDetails?,
    val removeAdsDetails: ProductDetails?,
    val vipDetails: ProductDetails?,
) {

    companion object {
        fun create(info: ProductDetailsInfo?): StoreScreenDetails? {
            if (info == null) return null
            val inAppDetails = info.inAppDetails?.associate {
                it.productId to it
            }
            val subsDetails = info.subscriptionDetails?.associate {
                it.productId to it
            }

            return StoreScreenDetails(
                inAppDetails?.get(BillingProductType.BEST_CHOICE_OFFER.id),
                inAppDetails?.get(BillingProductType.SMARTEST_OFFER.id),
                inAppDetails?.get(BillingProductType.CURRENCY_250.id),
                inAppDetails?.get(BillingProductType.CURRENCY_500.id),
                inAppDetails?.get(BillingProductType.CURRENCY_1000.id),
                inAppDetails?.get(BillingProductType.REMOVE_ADS.id),
                subsDetails?.get(BillingProductType.VIP.id),
            )
        }
    }
}
