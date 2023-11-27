package com.gamovation.core.data.repository

import com.gamovation.core.database.preferences.UserInfoPreferencesDataStore
import com.gamovation.core.domain.billing.UserVipType
import com.gamovation.core.domain.repository.UserInfoPreferencesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class OfflineUserInfoPreferencesRepository @Inject constructor(
    private val userInfoPreferencesDataStore: UserInfoPreferencesDataStore
) : UserInfoPreferencesRepository {
    override fun getUserCurrency(): Flow<Int> {
        return userInfoPreferencesDataStore.getUserCurrency()
    }

    override suspend fun setUserCurrency(currency: Int) {
        userInfoPreferencesDataStore.setUserCurrency(currency)
    }

    override suspend fun spendCurrency(amount: Int) {
        userInfoPreferencesDataStore.spendCurrency(amount)
    }

    override suspend fun buyCurrency(amount: Int) {
        userInfoPreferencesDataStore.buyCurrency(amount)
    }

    override fun getUserVipType(): Flow<UserVipType> {
        return userInfoPreferencesDataStore.getUserVip()
    }

    override suspend fun setUserVipType(type: UserVipType) {
        userInfoPreferencesDataStore.setUserVip(type)
    }

    override fun getIsAvailableForReview(): Flow<Boolean> {
        return userInfoPreferencesDataStore.getIsAvailableForReview()
    }

    override suspend fun setIsAvailableForReview(newValue: Boolean) {
        userInfoPreferencesDataStore.setIsAvailableForReview(newValue)
    }

    override suspend fun setLanguage(language: String) {
        userInfoPreferencesDataStore.setLanguage(language)
    }

    override fun getLanguage(): Flow<String> {
        return userInfoPreferencesDataStore.getLanguage()
    }

    override fun getNotificationPermissionTryCount(): Flow<Int> {
        return userInfoPreferencesDataStore.getNotificationPermissionTryCount()
    }

    override suspend fun increaseNotificationPermissionTryCount() {
        userInfoPreferencesDataStore.increaseNotificationPermissionTryCount()
    }

    override suspend fun resetNotificationRequestCount() {
        userInfoPreferencesDataStore.resetNotificationPermissionTryCount()
    }
}
