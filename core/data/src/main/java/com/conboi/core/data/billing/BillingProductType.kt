package com.conboi.core.data.billing

import com.android.billingclient.api.BillingClient

enum class BillingProductType(val id: String, val productType: String) {
    COOLEST_OFFER("coolest_offer", BillingClient.ProductType.INAPP),
    BEST_CHOICE_OFFER("best_choice_offer", BillingClient.ProductType.INAPP),
    CURRENCY_250("currency_250", BillingClient.ProductType.INAPP),
    CURRENCY_500("currency_500", BillingClient.ProductType.INAPP),
    CURRENCY_1000("currency_1000", BillingClient.ProductType.INAPP),
    REMOVE_ADS("remove_ads", BillingClient.ProductType.INAPP),
    VIP("vip_subscription", BillingClient.ProductType.SUBS),
}