package com.conboi.core.data.repository

import com.conboi.core.database.dao.LevelDataDao
import com.conboi.core.database.model.LevelData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineLevelDataRepository @Inject constructor(
    private val levelDataDao: LevelDataDao
) : LevelDataRepository {
    override suspend fun upsertLevelData(levelData: LevelData) {
        levelDataDao.upsertLevelData(levelData)
    }

    override suspend fun insertLevelsData(list: List<LevelData>) {
        levelDataDao.insertLevelsData(list)
    }

    override fun getLevelDataById(id: Int): Flow<LevelData> {
        return levelDataDao.getLevelDataById(id)
    }

    override fun getAllLevelData(): Flow<List<LevelData>> {
        return levelDataDao.getAllLevelData()
    }

    override fun getLastUncompletedLevel(): Flow<LevelData> {
        return levelDataDao.getLastUncompletedLevel()
    }

    override fun getLevelDataListByIndex(index: Int): Flow<List<LevelData>> {
        return levelDataDao.getLevelDataListByIndex(index)
    }

}