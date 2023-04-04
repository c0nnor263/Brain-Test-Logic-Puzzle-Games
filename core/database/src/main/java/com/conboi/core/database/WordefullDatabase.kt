package com.conboi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conboi.core.database.dao.LevelDataDao
import com.conboi.core.database.model.LevelData
import javax.inject.Inject
import javax.inject.Provider

@Database(
    version = 1,
    entities = [LevelData::class],
    exportSchema = false,
//    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class WordefullDatabase : RoomDatabase() {
    abstract fun levelDataDao(): LevelDataDao


    class Callback @Inject constructor(
        private val databaseProvider: Provider<WordefullDatabase>
    ) : RoomDatabase.Callback()


    companion object {
        const val DATABASE_NAME = "wordefull.db"
    }
}