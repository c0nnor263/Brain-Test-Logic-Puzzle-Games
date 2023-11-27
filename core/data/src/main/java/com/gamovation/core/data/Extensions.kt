package com.gamovation.core.data

import com.android.billingclient.api.ProductDetails

fun ProductDetails?.price(): String {
    return this?.oneTimePurchaseOfferDetails?.formattedPrice ?: ""
}
