package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import java.util.Locale

val LocalLocale = compositionLocalOf<Locale> {
    Locale.getDefault()
}