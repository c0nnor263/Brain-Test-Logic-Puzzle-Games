package com.conboi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.conboi.core.database.dao.LevelDataDao
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.MAX_LEVEL_ID
import com.conboi.core.domain.level.listOfLevelAdvises
import com.conboi.core.domain.level.listOfLevelTitles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
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
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val database = databaseProvider.get()
            CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
                val list = MutableList(MAX_LEVEL_ID - 1) { index ->
                    LevelData(
                        isLocked = if (BuildConfig.DEBUG) false else index != 0,
                        title = listOfLevelTitles[index],
                        advise = listOfLevelAdvises[index],

                        )
                }
                database.levelDataDao().insertLevelsData(list)
            }
        }
    }


    companion object {
        const val DATABASE_NAME = "wordefull.db"
    }
}