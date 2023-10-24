package com.gamovation.core.domain.repository

import com.gamovation.core.domain.billing.UserVipType
import kotlinx.coroutines.flow.Flow

interface UserInfoPreferencesRepository {
    fun getUserCurrency(): Flow<Int>
    suspend fun setUserCurrency(currency: Int)
    suspend fun spendCurrency(amount: Int)
    suspend fun buyCurrency(amount: Int)
    fun getUserVipType(): Flow<UserVipType>
    suspend fun setUserVipType(type: UserVipType)

    fun getIsAvailableForReview(): Flow<Boolean>
    suspend fun setIsAvailableForReview(newValue: Boolean)

    suspend fun setLanguage(language: String)

    fun getLanguage(): Flow<String>
}