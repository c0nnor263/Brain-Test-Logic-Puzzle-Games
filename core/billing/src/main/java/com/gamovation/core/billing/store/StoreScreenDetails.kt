package com.gamovation.core.billing.store

import com.gamovation.core.billing.BillingProductType
import com.gamovation.core.billing.ProductDetailsInfo
import com.gamovation.core.billing.model.StoreItemInfo

data class StoreScreenDetails(
    val bestChoiceDetails: StoreItemInfo?,
    val smartestOfferDetails: StoreItemInfo?,
    val currency250Details: StoreItemInfo?,
    val currency500Details: StoreItemInfo?,
    val currency1000Details: StoreItemInfo?,
    val removeAdsDetails: StoreItemInfo?,
    val vipDetails: StoreItemInfo?
) {

    companion object {
        fun create(info: ProductDetailsInfo?): StoreScreenDetails? {
            if (info == null) return null

            // Combining in-app and subscription details into a single map
            val allDetails = (info.inAppDetails.orEmpty() + info.subscriptionDetails.orEmpty())
                .associateBy { it.productId }

            // Creating a map for all store items from the combined details
            val storeDetails = BillingProductType.entries.associateWith { type ->
                StoreItemInfo(allDetails[type.id], type)
            }

            return StoreScreenDetails(
                bestChoiceDetails = storeDetails[BillingProductType.BEST_CHOICE_OFFER],
                smartestOfferDetails = storeDetails[BillingProductType.SMARTEST_OFFER],
                currency250Details = storeDetails[BillingProductType.CURRENCY_250],
                currency500Details = storeDetails[BillingProductType.CURRENCY_500],
                currency1000Details = storeDetails[BillingProductType.CURRENCY_1000],
                removeAdsDetails = storeDetails[BillingProductType.REMOVE_ADS],
                vipDetails = storeDetails[BillingProductType.VIP]
            )
        }
    }
}
