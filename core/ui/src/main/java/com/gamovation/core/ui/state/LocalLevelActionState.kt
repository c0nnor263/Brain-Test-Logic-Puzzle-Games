package com.gamovation.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.gamovation.core.domain.level.LevelActionState

val LocalLevelActionState = compositionLocalOf {
    LevelActionState.IDLE
}