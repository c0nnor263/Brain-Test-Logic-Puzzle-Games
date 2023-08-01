package com.conboi.core.ui.level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class UserInteraction {
    object OnBack : UserInteraction()
    object OnForward : UserInteraction()
    object OnWatchAd : UserInteraction()
    data class OnUpdateLevelActionState(val levelActionState: LevelActionState) : UserInteraction()
    data class OnUpdateLevelScreenState(val levelScreenState: LevelScreenState) : UserInteraction()
}

class UserInteractionState {
    private val _interactionFlow = MutableStateFlow<UserInteraction?>(null)
    val interactionFlow = _interactionFlow.asStateFlow()

    private fun updateInteraction(interaction: UserInteraction?) {
        _interactionFlow.value = interaction
    }

    fun onBack() = updateInteraction(UserInteraction.OnBack)
    fun onForward() = updateInteraction(UserInteraction.OnForward)
    fun onWatchAd() = updateInteraction(UserInteraction.OnWatchAd)
    fun onUpdateLevelActionState(levelActionState: LevelActionState) =
        updateInteraction(UserInteraction.OnUpdateLevelActionState(levelActionState))

    fun onUpdateLevelScreenState(levelScreenState: LevelScreenState) =
        updateInteraction(UserInteraction.OnUpdateLevelScreenState(levelScreenState))
}

@Composable
fun rememberUserInteraction(): UserInteractionState {
    return rememberSaveable {
        UserInteractionState()
    }
}