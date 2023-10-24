package com.gamovation.core.database.di

import android.app.Application
import androidx.room.Room
import com.gamovation.core.database.WordefullDatabase
import com.gamovation.core.database.data.LevelManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideWordefullDatabase(
        application: Application,
        callback: LevelManager,
    ): WordefullDatabase =
        Room.databaseBuilder(
            application,
            WordefullDatabase::class.java,
            WordefullDatabase.DATABASE_NAME
        )
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun provideLevelDataDao(database: WordefullDatabase) = database.levelDataDao()
}