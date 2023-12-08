package com.gamovation.feature.level.domain.model

import androidx.annotation.StringRes
import com.gamovation.core.database.model.LevelData

data class LevelUiDetails(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val advise: Int,
    val isCompleted: Boolean,
    val isHasAdvise: Boolean,
    val isLocked: Boolean
) {
    companion object {
        fun mapToUiDetails(levelData: LevelData): LevelUiDetails {
            return LevelUiDetails(
                id = levelData.id,
                title = levelData.title,
                advise = levelData.advise,
                isCompleted = levelData.isCompleted,
                isHasAdvise = levelData.isHasAdvise,
                isLocked = levelData.isLocked
            )
        }
    }
}
