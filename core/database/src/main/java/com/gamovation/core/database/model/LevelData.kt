package com.gamovation.core.database.model

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gamovation.core.database.BuildConfig

@Entity
data class LevelData(
 @PrimaryKey val id: Int,
 @StringRes val title: Int,
 @StringRes val advise: Int,
 val isCompleted: Boolean = false,
 val isHasAdvise: Boolean = false,
 val isLocked: Boolean = !BuildConfig.DEBUG,
)