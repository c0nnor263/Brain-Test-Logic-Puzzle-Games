package com.gamovation.core.database.data

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gamovation.core.database.WordefullDatabase
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.R
import com.gamovation.core.domain.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Singleton
class LevelManager @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val databaseProvider: Provider<WordefullDatabase>
) : RoomDatabase.Callback() {

    private val levels = listOf(
        LevelData(
            id = 1,
            title = R.string.l1_title,
            advise = R.string.l1_advise,
            isLocked = false
        ),
        LevelData(
            id = 2,
            title = R.string.l2_title,
            advise = R.string.l2_advise
        ),
        LevelData(
            id = 3,
            title = R.string.l3_title,
            advise = R.string.l3_advise
        ),
        LevelData(
            id = 4,
            title = R.string.l4_title,
            advise = R.string.l4_advise
        ),
        LevelData(
            id = 5,
            title = R.string.l5_title,
            advise = R.string.l5_advise
        ),
        LevelData(
            id = 6,
            title = R.string.l6_title,
            advise = R.string.l6_advise
        ),
        LevelData(
            id = 7,
            title = R.string.l7_title,
            advise = R.string.l7_advise
        ),
        LevelData(
            id = 8,
            title = R.string.l8_title,
            advise = R.string.l8_advise
        ),
        LevelData(
            id = 9,
            title = R.string.l9_title,
            advise = R.string.l9_advise
        ),
        LevelData(
            id = 10,
            title = R.string.l10_title,
            advise = R.string.l10_advise
        ),
        LevelData(
            id = 11,
            title = R.string.empty,
            advise = R.string.l11_advise
        ),
        LevelData(
            id = 12,
            title = R.string.l12_title,
            advise = R.string.l12_advise
        ),
        LevelData(
            id = 13,
            title = R.string.l13_title,
            advise = R.string.l13_advise
        ),
        LevelData(
            id = 14,
            title = R.string.empty,
            advise = R.string.l14_advise
        ),
        LevelData(
            id = 15,
            title = R.string.l15_title,
            advise = R.string.l15_advise
        ),
        LevelData(
            id = 16,
            title = R.string.l16_title,
            advise = R.string.l16_advise
        ),
        LevelData(
            id = 17,
            title = R.string.l17_title,
            advise = R.string.l17_advise
        ),
        LevelData(
            id = 18,
            title = R.string.l18_title,
            advise = R.string.l18_advise
        ),
        LevelData(
            id = 19,
            title = R.string.l19_title,
            advise = R.string.l19_advise
        ),
        LevelData(
            id = 20,
            title = R.string.l20_title,
            advise = R.string.l20_advise
        )
    )

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            databaseProvider.get().levelDataDao().insertAll(levels)
        }
    }

    companion object {
        const val MAX_LEVEL_ID = 20

        const val DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION = 2000L
        const val DEFAULT_LEVEL_SCREEN_RESTART_DURATION = 300L
    }
}
