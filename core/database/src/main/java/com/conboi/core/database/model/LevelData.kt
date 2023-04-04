package com.conboi.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isCompleted: Boolean = false,
    val isLocked: Boolean = true,
)