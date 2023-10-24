package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.level.LevelScreenState

val LocalLevelScreen = compositionLocalOf {
    LevelScreenState.IS_PLAYING
}