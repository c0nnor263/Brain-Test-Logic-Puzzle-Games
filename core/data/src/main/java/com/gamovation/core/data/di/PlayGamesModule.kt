package com.gamovation.core.data.di

import com.gamovation.core.data.repository.PlayGamesEventRepositoryImpl
import com.gamovation.core.domain.repository.PlayGamesEventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PlayGamesModule {

    @Binds
    fun providePlayGamesEventRepository(
        playGamesEventRepositoryImpl: PlayGamesEventRepositoryImpl
    ): PlayGamesEventRepository
}
