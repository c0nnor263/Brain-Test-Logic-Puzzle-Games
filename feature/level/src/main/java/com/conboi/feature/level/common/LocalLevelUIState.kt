package com.conboi.feature.level.common

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.ui.LevelUIState

val LocalLevelUIState = compositionLocalOf {
    LevelUIState.PROCESSING
}