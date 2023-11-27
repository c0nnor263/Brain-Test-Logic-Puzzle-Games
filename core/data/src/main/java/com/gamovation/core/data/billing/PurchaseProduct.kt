package com.gamovation.core.data.billing

data class PurchaseProduct(
    val type: BillingProductType? = null,
    val result: VerifyResult? = null
)

enum class VerifyResult {
    SUCCESS,
    PENDING,
    FAILED,
    CANCELLED
}
