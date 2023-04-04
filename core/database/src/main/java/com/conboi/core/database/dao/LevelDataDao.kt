package com.conboi.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.conboi.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDataDao {
    @Upsert
    suspend fun upsertLevelData(levelData: LevelData)

    @Query("SELECT * FROM leveldata WHERE id = :id")
    fun getLevelDataById(id: Int): Flow<LevelData>

    @Query("SELECT * FROM leveldata")
    fun getAllLevelData(): Flow<List<LevelData>>


    @Query("SELECT * FROM leveldata WHERE isCompleted = 0 ORDER BY id ASC LIMIT 1")
    fun getFirstUncompletedLevelId(): Flow<LevelData>
}