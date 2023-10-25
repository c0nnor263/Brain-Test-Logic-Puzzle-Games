package com.gamovation.tilecl.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gamovation.core.data.billing.BillingDataSource
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
    private val billingDataSource: BillingDataSource
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

}