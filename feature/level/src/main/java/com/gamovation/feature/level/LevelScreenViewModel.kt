package com.gamovation.feature.level

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.repository.OfflineLevelManagerRepository
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.gamovation.core.data.review.ReviewDataManager
import com.gamovation.core.database.data.LevelManager.Companion.MAX_LEVEL_ID
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.domain.repository.PlayGamesEventRepository
import com.gamovation.core.ui.DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION
import com.gamovation.feature.level.domain.model.LevelUiDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModel @Inject constructor(
    private val levelRepositoryImpl: OfflineLevelManagerRepository,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
    private val reviewDataManager: ReviewDataManager,
    private val playGamesEventRepository: PlayGamesEventRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState.asStateFlow()

    private var levelIndex = 1

    private val _levelUiDetails = MutableStateFlow<LevelUiDetails?>(null)
    val levelUiDetails: StateFlow<LevelUiDetails?> = _levelUiDetails

    private val _levelScreenState: MutableStateFlow<LevelScreenState> =
        MutableStateFlow(LevelScreenState.IsLevelPlaying)
    val levelScreenState: StateFlow<LevelScreenState> = _levelScreenState

    private val _levelActionState = MutableStateFlow(LevelActionState.IDLE)
    val levelActionState: StateFlow<LevelActionState> = _levelActionState

    fun updateLevelScreenState(state: LevelScreenState) = viewModelScope.launch(Dispatchers.Main) {
        checkForPlayGamesSubmitEvent(state)
        _levelScreenState.emit(state)

        // If state is not equals to next level, do some delay
        if (state != LevelScreenState.ProceedToNextLevel) {
            delay(DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION)
        }

        // If user completed level with correct choice, update state to completed, otherwise update state to is playing
        val newState = when (state) {
            is LevelScreenState.CompletedTheGame -> {
                completeLevel()
                state
            }

            is LevelScreenState.UserCorrectChoice -> {
                LevelScreenState.LevelCompleted
            }

            else -> LevelScreenState.IsLevelPlaying
        }

        _levelScreenState.emit(newState)
    }

    private fun checkForPlayGamesSubmitEvent(state: LevelScreenState) {
        when (state) {
            is LevelScreenState.CompletedTheGame -> {
                submitEvent(state.eventId)
            }

            is LevelScreenState.UserCorrectChoice -> {
                submitEvent(state.eventId)
            }

            is LevelScreenState.UserWatchAd -> {
                submitEvent(state.eventId)
            }

            is LevelScreenState.UserWrongChoice -> {
                submitEvent(state.eventId)
            }

            else -> {}
        }
    }

    fun updateLevelActionState(state: LevelActionState) = viewModelScope.launch(Dispatchers.Main) {
        // If state is not playing, do not update action state
        if (levelScreenState.value != LevelScreenState.IsLevelPlaying) return@launch
        _levelActionState.emit(state)
    }

    fun updateLevelIndex(newIndex: Int, newLevelData: LevelData? = null) =
        viewModelScope.launch(Dispatchers.IO) {
            levelIndex = newIndex

            // Get level data for current index
            val data =
                newLevelData ?: levelRepositoryImpl.getById(levelIndex).first()
            val details = LevelUiDetails.mapToUiDetails(data)
            _levelUiDetails.emit(details)
        }

    fun onForwardLevel() = viewModelScope.launch(Dispatchers.IO) {
        // Completing current level and unlocking next level
        completeLevel()
        setNextLevel()
    }

    fun onBackLevel() {
        val previousLevelIndex = (levelIndex - 1).coerceAtLeast(1)
        updateLevelIndex(previousLevelIndex)
    }

    private fun completeLevel() = viewModelScope.launch(Dispatchers.IO) {
        val levelData = levelRepositoryImpl.getById(levelIndex).first().copy(
            isCompleted = true,
            isHasAdvise = false
        )
        levelRepositoryImpl.upsert(levelData)
    }

    private suspend fun setNextLevel() {
        val nextLevelIndex = (levelIndex + 1).coerceAtMost(MAX_LEVEL_ID)
        val nextData = levelRepositoryImpl.getById(nextLevelIndex).first().copy(
            isLocked = false
        )

        // Update current and next level data

        levelRepositoryImpl.upsert(nextData)
        // Move to next level
        updateLevelIndex(nextLevelIndex, nextData)
    }

    fun buyAction(cost: Int) = viewModelScope.launch(Dispatchers.IO) {
        // Spend currency for action

        when (levelActionState.value) {
            LevelActionState.ADVICE -> {
                // Update level advice status to true
                val levelData = levelRepositoryImpl.getById(levelIndex).first().copy(
                    isHasAdvise = true
                )
                val details = LevelUiDetails.mapToUiDetails(levelData)
                levelRepositoryImpl.upsert(levelData)
                _levelUiDetails.emit(details)

                submitEvent(com.gamovation.core.domain.R.string.event_advice_action_use)
            }

            LevelActionState.SKIP -> {
                updateLevelScreenState(LevelScreenState.LevelCompleted)
                submitEvent(com.gamovation.core.domain.R.string.event_skip_action_use)
            }

            else -> {}
        }
        userInfoPreferencesRepository.spendCurrency(cost)
    }

    fun watchAdReward() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.buyCurrency(25)
        updateLevelScreenState(LevelScreenState.ProceedToNextLevel)
    }

    fun processReviewRequest(activity: ComponentActivity, showDialog: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {
            reviewDataManager.requestReviewInfo(activity, showDialog)
        }

    fun submitEvent(@StringRes event: Int, value: Int = 1) {
        playGamesEventRepository.submitEvent(event, value)
    }

    fun updateUiState(state: UiState) {
        _uiState.value = state
    }

    fun clearUiState() {
        _uiState.value = null
    }

    sealed class UiState {
        data object OnReviewRequest : UiState()
        data object OnBack : UiState()
        data object OnForward : UiState()
        data object OnWatchAd : UiState()
        data class OnUpdateLevelActionState(val actionState: LevelActionState) : UiState()
        data class OnUpdateLevelScreenState(val screenState: LevelScreenState) : UiState()
    }
}
