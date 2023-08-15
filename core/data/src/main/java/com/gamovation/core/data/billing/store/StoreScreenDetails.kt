package com.gamovation.core.data.billing.store

import com.android.billingclient.api.ProductDetails
import com.gamovation.core.data.billing.ProductDetailsInfo

data class StoreScreenDetails(
    val bestChoiceDetails: ProductDetails?,
    val coolestOfferDetails: ProductDetails?,
    val currency250Details: ProductDetails?,
    val currency500Details: ProductDetails?,
    val currency1000Details: ProductDetails?,
    val removeAdsDetails: ProductDetails?,
    val vipDetails: ProductDetails?,
) {

    companion object {
        fun create(info: ProductDetailsInfo?): StoreScreenDetails? {
            if (info == null) return null
            val inAppDetails = info.inAppDetails?.sortedBy {
                it.productId
            }
            val subsDetails = info.subscriptionDetails?.sortedBy {
                it.productId
            }

            return StoreScreenDetails(
                inAppDetails?.getOrNull(0),
                inAppDetails?.getOrNull(1),
                inAppDetails?.getOrNull(2),
                inAppDetails?.getOrNull(3),
                inAppDetails?.getOrNull(4),
                inAppDetails?.getOrNull(5),
                subsDetails?.getOrNull(0),
            )

        }
    }
}

