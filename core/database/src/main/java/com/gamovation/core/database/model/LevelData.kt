package com.gamovation.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val advise: String = "",
    val isCompleted: Boolean = false,
    val isLocked: Boolean = true,
    val isHasAdvise: Boolean = false,
)