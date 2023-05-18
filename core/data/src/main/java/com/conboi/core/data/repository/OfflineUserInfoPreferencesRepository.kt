package com.conboi.core.data.repository

import com.conboi.core.database.preferences.UserInfoPreferencesDataStore
import com.conboi.core.domain.billing.UserVipType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
}