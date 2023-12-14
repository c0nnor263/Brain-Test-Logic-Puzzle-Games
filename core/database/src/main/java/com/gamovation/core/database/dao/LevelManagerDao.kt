package com.gamovation.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.gamovation.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelManagerDao {
    @Upsert
    suspend fun upsert(levelData: LevelData)

    @Insert
    suspend fun insertAll(list: List<LevelData>)

    @Query("SELECT * FROM leveldata WHERE id = :id")
    fun getById(id: Int): Flow<LevelData>

    @Query("SELECT * FROM leveldata WHERE isLocked = 0")
    fun getAll(): Flow<List<LevelData>>

    @Query("SELECT * FROM leveldata WHERE isCompleted = 0 ORDER BY id ASC LIMIT 1")
    fun getLastUncompleted(): Flow<LevelData>
}
