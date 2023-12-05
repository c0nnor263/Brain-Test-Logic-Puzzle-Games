package com.gamovation.core.data.model

import com.android.billingclient.api.ProductDetails
import com.gamovation.core.data.billing.BillingProductType

data class StoreItemInfo(
    val details: ProductDetails?,
    val type: BillingProductType
)
