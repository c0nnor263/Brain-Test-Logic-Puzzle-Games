package com.conboi.core.database.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.conboi.core.domain.currency.DEFAULT_USER_CURRENCY
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

}