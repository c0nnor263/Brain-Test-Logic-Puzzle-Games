package com.conboi.core.domain.currency

import com.conboi.core.domain.billing.UserVipType

data class CostsInfo(
    val adviceCost: Int = DEFAULT_ADVICE_COST,
    val skipCost: Int = DEFAULT_SKIP_COST,
)

fun CostsInfo.calculateCosts(type: UserVipType): CostsInfo {
    return when (type) {
        UserVipType.PREMIUM -> this.copy(
            adviceCost = adviceCost - 20,
            skipCost = skipCost - 25
        )

        else -> this
    }
}