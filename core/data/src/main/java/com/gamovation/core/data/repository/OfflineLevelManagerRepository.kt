package com.gamovation.core.data.repository

import com.gamovation.core.database.dao.LevelManagerDao
import com.gamovation.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineLevelManagerRepository @Inject constructor(
    private val levelManagerDao: LevelManagerDao
) : LevelManagerRepository {
    override suspend fun upsert(levelData: LevelData) {
        levelManagerDao.upsert(levelData)
    }

    override suspend fun insertAll(list: List<LevelData>) {
        levelManagerDao.insertAll(list)
    }

    override fun getById(id: Int): Flow<LevelData> {
        return levelManagerDao.getById(id)
    }

    override fun getAll(): Flow<List<LevelData>> {
        return levelManagerDao.getAll()
    }

    override fun getLastUncompleted(): Flow<LevelData> {
        return levelManagerDao.getLastUncompleted()
    }
}
