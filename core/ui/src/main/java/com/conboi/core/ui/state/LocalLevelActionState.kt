package com.conboi.core.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.conboi.core.domain.level.LevelActionState

val LocalLevelActionState = compositionLocalOf {
    LevelActionState.IDLE
}