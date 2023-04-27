package com.conboi.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.level.LevelScreenState

val LocalLevelScreenState = compositionLocalOf {
    LevelScreenState.IS_PLAYING
}