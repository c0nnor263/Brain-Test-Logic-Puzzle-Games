package com.gamovation.feature.level

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.repository.OfflineLevelDataRepository
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.gamovation.core.data.review.ReviewDataManager
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.domain.level.MAX_LEVEL_ID
import com.gamovation.core.ui.level.UserInteraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModel @Inject constructor(
    private val levelRepositoryImpl: OfflineLevelDataRepository,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
    private val reviewDataManager: ReviewDataManager
) : ViewModel() {
    private var levelIndex = 1

    private val _levelData = MutableStateFlow<LevelData?>(null)
    val levelData: StateFlow<LevelData?> = _levelData

    private val _levelScreenState = MutableStateFlow(LevelScreenState.IS_PLAYING)
    val levelScreenState: StateFlow<LevelScreenState> = _levelScreenState

    private val _levelActionState = MutableStateFlow(LevelActionState.IDLE)
    val levelActionState: StateFlow<LevelActionState> = _levelActionState


    fun updateLevelScreenState(state: LevelScreenState) = viewModelScope.launch(Dispatchers.Main) {
        _levelScreenState.emit(state)

        // If state is not equals to next level, do some delay
        if (state != LevelScreenState.NEXT_LEVEL) delay(DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION)

        // If user completed level with correct choice, update state to completed, otherwise update state to is playing
        val newState = when (state) {
            LevelScreenState.FINAL -> {
                withContext(Dispatchers.IO) {
                    completeLevel()
                }
                LevelScreenState.FINAL
            }

            LevelScreenState.CORRECT_CHOICE -> LevelScreenState.COMPLETED
            else -> LevelScreenState.IS_PLAYING
        }
        Log.i("TAG", "updateLevelScreenState: NEW $newState")
        _levelScreenState.emit(newState)
    }

    fun updateLevelActionState(state: LevelActionState) = viewModelScope.launch(Dispatchers.Main) {
        // If state is not playing, do not update action state
        if (levelScreenState.value != LevelScreenState.IS_PLAYING) return@launch
        _levelActionState.emit(state)
    }

    fun updateLevelIndex(newIndex: Int, newLevelData: LevelData? = null) =
        viewModelScope.launch(Dispatchers.IO) {
            levelIndex = newIndex

            // Get level data for current index
            val data =
                newLevelData ?: levelRepositoryImpl.getLevelDataById(levelIndex).first()
            _levelData.emit(data)
        }


    fun onForwardLevel() = viewModelScope.launch(Dispatchers.IO) {
        // Completing current level and unlocking next level
        completeLevel()
        setNextLevel()
    }


    suspend fun completeLevel() {
        val levelData = levelRepositoryImpl.getLevelDataById(levelIndex).first().copy(
            isCompleted = true, isHasAdvise = false
        )
        levelRepositoryImpl.upsertLevelData(levelData)
    }

    suspend fun setNextLevel() {
        val nextLevelIndex = (levelIndex + 1).coerceAtMost(MAX_LEVEL_ID)
        val nextData = levelRepositoryImpl.getLevelDataById(nextLevelIndex).first().copy(
            isLocked = false,
        )

        // Update current and next level data

        levelRepositoryImpl.upsertLevelData(nextData)
        // Move to next level
        updateLevelIndex(nextLevelIndex, nextData)
    }

    fun onBackLevel() {
        val previousLevelIndex = (levelIndex - 1).coerceAtLeast(1)
        updateLevelIndex(previousLevelIndex)
    }

    fun buyAction(cost: Int) = viewModelScope.launch(Dispatchers.IO) {
        // Spend currency for action


        when (levelActionState.value) {
            LevelActionState.ADVICE -> {
                // Update level advice status to true
                val levelData = levelRepositoryImpl.getLevelDataById(levelIndex).first().copy(
                    isHasAdvise = true
                )
                levelRepositoryImpl.upsertLevelData(levelData)
                _levelData.emit(levelData)
            }

            LevelActionState.SKIP -> updateLevelScreenState(LevelScreenState.COMPLETED)
            else -> {}
        }
        userInfoPreferencesRepository.spendCurrency(cost)
    }


    fun watchAdReward() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.buyCurrency(25)
        updateLevelScreenState(LevelScreenState.NEXT_LEVEL)
    }

    fun processInteraction(interaction: UserInteraction?) {
        when (interaction) {
            UserInteraction.OnBack -> onBackLevel()
            UserInteraction.OnForward -> onForwardLevel()
            UserInteraction.OnWatchAd -> watchAdReward()
            is UserInteraction.OnUpdateLevelActionState -> {
                updateLevelActionState(interaction.levelActionState)
            }

            is UserInteraction.OnUpdateLevelScreenState -> {
                updateLevelScreenState(interaction.levelScreenState)
            }

            null -> {}
        }
    }

    fun processReviewRequest(activity: ComponentActivity, showDialog: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {
            reviewDataManager.requestReviewInfo(activity, showDialog)
        }


}