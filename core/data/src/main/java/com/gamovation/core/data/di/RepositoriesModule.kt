package com.gamovation.core.data.di

import com.gamovation.core.data.repository.LevelManagerRepository
import com.gamovation.core.data.repository.OfflineLevelManagerRepository
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.gamovation.core.data.repository.PlayGamesEventRepositoryImpl
import com.gamovation.core.domain.repository.PlayGamesEventRepository
import com.gamovation.core.domain.repository.UserInfoPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindLevelManagerRepository(
        offlineLevelManagerRepository: OfflineLevelManagerRepository
    ): LevelManagerRepository

    @Binds
    fun bindUserInfoPreferencesRepository(
        offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
    ): UserInfoPreferencesRepository

    @Binds
    fun bindPlayGamesEventRepository(
        playGamesEventRepositoryImpl: PlayGamesEventRepositoryImpl
    ): PlayGamesEventRepository


}