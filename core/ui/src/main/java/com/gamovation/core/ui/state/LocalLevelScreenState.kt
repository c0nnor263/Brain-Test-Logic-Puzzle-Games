package com.gamovation.core.ui.state

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.level.LevelScreenState

val LocalLevelScreen: ProvidableCompositionLocal<LevelScreenState> = compositionLocalOf {
    LevelScreenState.IsLevelPlaying
}
