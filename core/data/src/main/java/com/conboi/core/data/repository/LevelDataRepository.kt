package com.conboi.core.data.repository

import com.conboi.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

interface LevelDataRepository {
    suspend fun upsertLevelData(levelData: LevelData)
    suspend fun insertLevelsData(list: List<LevelData>)
    fun getLevelDataById(id: Int): Flow<LevelData>
    fun getAllLevelData(): Flow<List<LevelData>>
    fun getLastUncompletedLevel(): Flow<LevelData>
    fun getLevelDataListByIndex(id: Int): Flow<List<LevelData>>

}