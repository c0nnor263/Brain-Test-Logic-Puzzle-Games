package com.gamovation.core.data.repository

import com.gamovation.core.database.dao.LevelDataDao
import com.gamovation.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineLevelDataRepository @Inject constructor(
    private val levelDataDao: LevelDataDao
) : LevelDataRepository {
    override suspend fun upsert(levelData: LevelData) {
        levelDataDao.upsert(levelData)
    }

    override suspend fun insertAll(list: List<LevelData>) {
        levelDataDao.insertAll(list)
    }

    override fun getById(id: Int): Flow<LevelData> {
        return levelDataDao.getById(id)
    }

    override fun getAll(): Flow<List<LevelData>> {
        return levelDataDao.getAll()
    }

    override fun getLastUncompleted(): Flow<LevelData> {
        return levelDataDao.getLastUncompleted()
    }

}