package com.gamovation.core.database.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
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
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoPreferencesDataStore @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        const val NAME = "user_info"
        const val DEFAULT_NOTIFICATION_PERMISSION_LIMIT = 3
        val Context.userInfoPreferencesDataStore by preferencesDataStore(name = NAME)
    }

    object UserInfoPreferencesKeys {
        val USER_CURRENCY = intPreferencesKey("user_currency")
        val USER_VIP = stringPreferencesKey("user_vip")
        val IS_AVAILABLE_REVIEW = booleanPreferencesKey("is_available_review")
        val LANGUAGE = stringPreferencesKey("language")
        val NOTIFICATION_PERMISSION_TRY_COUNT =
            intPreferencesKey("notification_permission_try_count")
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

    fun getIsAvailableForReview(): Flow<Boolean> {
        return combine(dataStore.data) {
            it.first()[UserInfoPreferencesKeys.IS_AVAILABLE_REVIEW] ?: true
        }
    }

    suspend fun setIsAvailableForReview(newValue: Boolean) {
        dataStore.edit { preferences ->
            preferences[UserInfoPreferencesKeys.IS_AVAILABLE_REVIEW] = newValue
        }
    }

    suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[UserInfoPreferencesKeys.LANGUAGE] = language
        }
    }

    fun getLanguage(): Flow<String> {
        return combine(dataStore.data) {
            it.first()[UserInfoPreferencesKeys.LANGUAGE] ?: Locale.getDefault().language
        }
    }

    fun getNotificationPermissionTryCount(): Flow<Int> {
        return combine(dataStore.data) {
            it.first()[UserInfoPreferencesKeys.NOTIFICATION_PERMISSION_TRY_COUNT] ?: 0
        }
    }

    suspend fun increaseNotificationPermissionTryCount() {
        dataStore.edit { preferences ->
            val current = getNotificationPermissionTryCount().first()
            preferences[UserInfoPreferencesKeys.NOTIFICATION_PERMISSION_TRY_COUNT] = current.plus(1)
        }
    }

    suspend fun resetNotificationPermissionTryCount() {
        dataStore.edit { preferences ->
            preferences[UserInfoPreferencesKeys.NOTIFICATION_PERMISSION_TRY_COUNT] = 0
        }
    }
}
