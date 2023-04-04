package com.conboi.core.database.di

import android.app.Application
import androidx.room.Room
import com.conboi.core.database.WordefullDatabase
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
        callback: WordefullDatabase.Callback
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