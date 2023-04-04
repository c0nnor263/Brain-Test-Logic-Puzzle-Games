package com.conboi.feature.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conboi.core.data.repository.OfflineLevelDataRepository
import com.conboi.core.domain.ui.LevelActionState
import com.conboi.core.domain.ui.LevelUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModel @Inject constructor(
    private val levelDataRepositoryImpl: OfflineLevelDataRepository
) : ViewModel() {

    private val _levelUIState: MutableStateFlow<LevelUIState> =
        MutableStateFlow(LevelUIState.PROCESSING)
    val levelUIState: StateFlow<LevelUIState> = _levelUIState

    private val _levelActionState = MutableStateFlow<LevelActionState?>(null)
    val levelActionState: StateFlow<LevelActionState?> = _levelActionState

    fun updateLevelUIState(state: LevelUIState) = viewModelScope.launch(Dispatchers.Main) {
        if (levelUIState.value != LevelUIState.PROCESSING) return@launch
        _levelUIState.emit(state)
        delay(DEFAULT_LEVEL_UI_COUNTDOWN_DURATION)
        _levelUIState.emit(LevelUIState.PROCESSING)
    }

    fun updateLevelActionState(state: LevelActionState?) = viewModelScope.launch(Dispatchers.Main) {
        _levelActionState.emit(state)
    }
}