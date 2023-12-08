package com.gamovation.tilecl.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
    private val billingDataSource: com.gamovation.core.billing.BillingDataSource
) : ViewModel() {

    var isViewInitialized by mutableStateOf(false)

    override fun onCleared() {
        super.onCleared()
        billingDataSource.endConnection()
    }

    init {
        billingDataSource.initClient()
    }

    fun getUserCurrency() = userInfoPreferencesRepository.getUserCurrency()

    fun getUserVipType() = userInfoPreferencesRepository.getUserVipType()

    fun getLanguage() = userInfoPreferencesRepository.getLanguage()
    fun onResumeBilling() {
        billingDataSource.onResumeBilling()
    }

    suspend fun getNotificationPermissionTryCount() = withContext(Dispatchers.IO) {
        userInfoPreferencesRepository.getNotificationPermissionTryCount().first()
    }

    fun increaseNotificationRequestCount() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.increaseNotificationPermissionTryCount()
    }

    fun resetNotificationRequestCount() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.resetNotificationRequestCount()
    }
}
