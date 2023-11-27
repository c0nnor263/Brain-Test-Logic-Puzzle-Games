package com.gamovation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.repository.OfflineLevelDataRepository
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val levelDataRepositoryImpl: OfflineLevelDataRepository,
    private val userInfoPreferencesDataStore: OfflineUserInfoPreferencesRepository
) : ViewModel() {

    fun getLastUncompleted() = levelDataRepositoryImpl.getLastUncompleted()

    suspend fun getNotificationPermissionTryCount() = withContext(Dispatchers.IO) {
        userInfoPreferencesDataStore.getNotificationPermissionTryCount().first()
    }

    fun increaseNotificationRequestCount() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesDataStore.increaseNotificationPermissionTryCount()
    }

    fun resetNotificationRequestCount() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesDataStore.resetNotificationRequestCount()
    }
}
