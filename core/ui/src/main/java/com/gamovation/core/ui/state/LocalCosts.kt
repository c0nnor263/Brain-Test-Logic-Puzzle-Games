package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.currency.CostsInfo

val LocalCosts = compositionLocalOf {
    CostsInfo()
}
