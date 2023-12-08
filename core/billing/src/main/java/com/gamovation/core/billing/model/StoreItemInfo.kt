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

fun StoreItemInfo?.price(): String {
    return this?.details?.oneTimePurchaseOfferDetails?.formattedPrice ?: ""
}
