package com.gamovation.core.billing.model

import com.android.billingclient.api.ProductDetails
import com.gamovation.core.billing.BillingProductType

data class StoreItemInfo(
    val details: ProductDetails?,
    val type: BillingProductType
)

fun StoreItemInfo?.isNotEmpty(): Boolean {
    return this?.details != null
}

fun StoreItemInfo?.price(isSubscription: Boolean = false): String {
    return if (!isSubscription) {
        this?.details?.oneTimePurchaseOfferDetails?.formattedPrice ?: ""
    } else {
        val subscriptionOfferDetails = this?.details?.subscriptionOfferDetails?.getOrNull(0)
        val pricingPhaseList = subscriptionOfferDetails?.pricingPhases?.pricingPhaseList
        pricingPhaseList?.getOrNull(0)?.formattedPrice ?: ""
    }
}