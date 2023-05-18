package com.conboi.wordefull.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conboi.core.data.billing.BillingDataSource
import com.conboi.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    private val offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
    private val billingDataSource: BillingDataSource
) : ViewModel() {

    init {
        billingDataSource.initClient()
    }

    fun getUserCurrency() = offlineUserInfoPreferencesRepository.getUserCurrency()

    fun getUserVipType() = offlineUserInfoPreferencesRepository.getUserVipType()
    fun queryProducts() = viewModelScope.launch(Dispatchers.IO) {
        billingDataSource.querySkuDetails()
    }

}