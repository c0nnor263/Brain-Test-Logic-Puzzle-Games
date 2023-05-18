package com.conboi.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.currency.CostsInfo

val LocalCosts = compositionLocalOf {
    CostsInfo()
}