package com.gamovation.core.database.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.gamovation.core.domain.billing.UserVipType
import com.gamovation.core.domain.currency.DEFAULT_USER_CURRENCY
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoPreferencesDataStore @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        const val NAME = "user_info"
        val Context.userInfoPreferencesDataStore by preferencesDataStore(name = NAME)
    }

    object UserInfoPreferencesKeys {
        val USER_CURRENCY = intPreferencesKey("user_currency")
        val USER_VIP = stringPreferencesKey("user_vip")
    }


    private val dataStore = context.userInfoPreferencesDataStore


    fun getUserCurrency(): Flow<Int> {
        return combine(dataStore.data) {
            it.first()[UserInfoPreferencesKeys.USER_CURRENCY] ?: DEFAULT_USER_CURRENCY
        }
    }

    suspend fun setUserCurrency(currency: Int) {
        dataStore.edit { preferences ->
            preferences[UserInfoPreferencesKeys.USER_CURRENCY] = currency
        }
    }

    suspend fun spendCurrency(amount: Int) {
        dataStore.edit { preferences ->
            val currency = getUserCurrency().first()
            preferences[UserInfoPreferencesKeys.USER_CURRENCY] = currency - amount
        }
    }

    suspend fun buyCurrency(amount: Int) {
        dataStore.edit { preferences ->
            val currency = getUserCurrency().first()
            preferences[UserInfoPreferencesKeys.USER_CURRENCY] = currency + amount
        }
    }


    fun getUserVip(): Flow<UserVipType> {
        return combine(dataStore.data) {
            val rawType = it.first()[UserInfoPreferencesKeys.USER_VIP]
            UserVipType.valueOf(rawType ?: UserVipType.BASE.name)
        }
    }

    suspend fun setUserVip(type: UserVipType) {
        dataStore.edit { preferences ->

            val newType = when (getUserVip().first()) {
                UserVipType.ADS_FREE -> if (type == UserVipType.PREMIUM) type else UserVipType.ADS_FREE
                else -> type
            }
            preferences[UserInfoPreferencesKeys.USER_VIP] = newType.name
        }
    }


}