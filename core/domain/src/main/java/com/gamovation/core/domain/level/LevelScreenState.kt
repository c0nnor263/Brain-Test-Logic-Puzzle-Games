package com.gamovation.core.domain.level

import androidx.annotation.StringRes

sealed class LevelScreenState {
    data class UserCorrectChoice(@StringRes val eventId: Int) : LevelScreenState()
    data class UserWrongChoice(@StringRes val eventId: Int) : LevelScreenState()
    data class UserWatchAd(@StringRes val eventId: Int) : LevelScreenState()

    data object IsLevelPlaying : LevelScreenState()
    data object LevelCompleted : LevelScreenState()
    data object ProceedToNextLevel : LevelScreenState()
    data class CompletedTheGame(@StringRes val eventId: Int) : LevelScreenState()
}
