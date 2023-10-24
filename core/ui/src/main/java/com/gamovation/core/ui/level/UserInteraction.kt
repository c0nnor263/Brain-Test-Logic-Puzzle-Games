package com.gamovation.core.ui.level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class UserInteraction {
    data object OnBack : UserInteraction()
    data object OnForward : UserInteraction()
    data object OnWatchAd : UserInteraction()
    data class OnUpdateLevelActionState(val levelActionState: LevelActionState) : UserInteraction()
    data class OnUpdateLevelScreenState(val levelScreenState: LevelScreenState) : UserInteraction()
}

class UserInteractionState {
    private val _interactionFlow = MutableStateFlow<UserInteraction?>(null)
    val interactionFlow = _interactionFlow.asStateFlow()

    private fun updateInteraction(interaction: UserInteraction?) {
        _interactionFlow.value = interaction
    }

    fun onIdle() = updateInteraction(null)
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
    return remember { mutableStateOf(UserInteractionState()) }.value
}