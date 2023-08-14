package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.currency.DEFAULT_USER_CURRENCY

val LocalCurrency = compositionLocalOf {
    DEFAULT_USER_CURRENCY
}