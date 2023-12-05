package com.gamovation.feature.store

import com.gamovation.core.data.model.StoreItemInfo

fun StoreItemInfo?.price(): String {
    return this?.details?.oneTimePurchaseOfferDetails?.formattedPrice ?: ""
}
