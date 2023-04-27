package com.conboi.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.currency.DEFAULT_USER_CURRENCY

val LocalCurrency = compositionLocalOf {
    DEFAULT_USER_CURRENCY
}