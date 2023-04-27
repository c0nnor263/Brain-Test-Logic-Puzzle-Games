package com.conboi.core.data.repository

import kotlinx.coroutines.flow.Flow

interface UserInfoPreferencesRepository {
    fun getUserCurrency(): Flow<Int>
    suspend fun setUserCurrency(currency: Int)
    suspend fun spendCurrency(amount: Int)
    suspend fun buyCurrency(amount: Int)
}