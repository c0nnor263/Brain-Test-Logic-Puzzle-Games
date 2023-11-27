package com.gamovation.core.data.repository

import com.gamovation.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

interface LevelDataRepository {
    suspend fun upsert(levelData: LevelData)
    suspend fun insertAll(list: List<LevelData>)
    fun getById(id: Int): Flow<LevelData>
    fun getAll(): Flow<List<LevelData>>
    fun getLastUncompleted(): Flow<LevelData>
}
