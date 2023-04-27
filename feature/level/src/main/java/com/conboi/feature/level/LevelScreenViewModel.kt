package com.conboi.feature.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conboi.core.data.repository.OfflineLevelDataRepository
import com.conboi.core.data.repository.OfflineUserInfoPreferencesRepository
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.currency.DEFAULT_ADVICE_COST
import com.conboi.core.domain.level.DEFAULT_LEVEL_UI_COUNTDOWN_DURATION
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.domain.level.MAX_LEVEL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModel @Inject constructor(
    private val levelRepositoryImpl: OfflineLevelDataRepository,
    private val offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) : ViewModel() {
    private var levelId = 1

    private val _levelData = MutableStateFlow<LevelData?>(null)
    val levelData: StateFlow<LevelData?> = _levelData

    private val _levelScreenState = MutableStateFlow(LevelScreenState.IS_PLAYING)
    val levelScreenState: StateFlow<LevelScreenState> = _levelScreenState

    private val _LevelActionState = MutableStateFlow(LevelActionState.IDLE)
    val levelActionState: StateFlow<LevelActionState> = _LevelActionState


    fun updateLevelScreenState(state: LevelScreenState) = viewModelScope.launch(Dispatchers.Main) {
        _levelScreenState.emit(state)
        if (state != LevelScreenState.NEXT_LEVEL) delay(DEFAULT_LEVEL_UI_COUNTDOWN_DURATION)

        val newState = if (state != LevelScreenState.CORRECT_CHOICE) {
            LevelScreenState.IS_PLAYING
        } else LevelScreenState.COMPLETED
        _levelScreenState.emit(newState)
    }

    fun updateLevelActionState(state: LevelActionState) = viewModelScope.launch(Dispatchers.Main) {
        if (levelScreenState.value != LevelScreenState.IS_PLAYING) return@launch
        _LevelActionState.emit(state)
    }

    fun updateLevelId(newLeveId: Int, newLevelData: LevelData? = null) =
        viewModelScope.launch(Dispatchers.IO) {
            levelId = newLeveId
            val data =
                newLevelData ?: levelRepositoryImpl.getLevelDataById(levelId).first()
            _levelData.emit(data)
        }


    fun onForward() = viewModelScope.launch(Dispatchers.IO) {
        val nextLevelId = (levelId + 1).coerceAtMost(MAX_LEVEL_ID)

        val levelData = levelRepositoryImpl.getLevelDataById(levelId).first().copy(
            isCompleted = true, isHasAdvise = false
        )
        val nextData = levelRepositoryImpl.getLevelDataById(nextLevelId).first().copy(
            isLocked = false,
        )

        levelRepositoryImpl.upsertLevelData(levelData)
        levelRepositoryImpl.upsertLevelData(nextData)

        updateLevelId(nextLevelId, nextData)
    }

    fun onBack() {
        val previousLevelId = (levelId - 1).coerceAtLeast(1)
        updateLevelId(previousLevelId)
    }

    fun buyAdvice() = viewModelScope.launch(Dispatchers.IO) {
        offlineUserInfoPreferencesRepository.spendCurrency(DEFAULT_ADVICE_COST)
        val levelData = levelRepositoryImpl.getLevelDataById(levelId).first().copy(
            isHasAdvise = true
        )
        levelRepositoryImpl.upsertLevelData(
            levelData
        )
        _levelData.emit(levelData)

    }


}