package com.conboi.core.data.repository

import com.conboi.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

interface LevelDataRepository {
    suspend fun upsertLevelData(levelData: LevelData)
    fun getLevelDataById(id: Int): Flow<LevelData>
    fun getAllLevelData(): Flow<List<LevelData>>
    fun getFirstUncompletedLevelId(): Flow<LevelData>
}