package com.gamovation.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.gamovation.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDataDao {
    @Upsert
    suspend fun upsertLevelData(levelData: LevelData)

    @Insert
    suspend fun insertLevelsData(list: List<LevelData>)

    @Query("SELECT * FROM leveldata WHERE id = :id")
    fun getLevelDataById(id: Int): Flow<LevelData>

    @Query("SELECT * FROM leveldata")
    fun getAllLevelData(): Flow<List<LevelData>>


    @Query("SELECT * FROM leveldata WHERE isCompleted = 0 ORDER BY id ASC LIMIT 1")
    fun getLastUncompletedLevel(): Flow<LevelData>

    @Query("SELECT * FROM leveldata ORDER BY id LIMIT 5 OFFSET :id")
    fun getLevelDataListByIndex(id: Int): Flow<List<LevelData>>
}