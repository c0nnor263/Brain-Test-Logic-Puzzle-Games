package com.gamovation.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.billing.BillingDataSource
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val billingDataSource: BillingDataSource,
    private val offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository,
) : ViewModel() {
    fun restorePurchases() {
        billingDataSource.restorePurchases()
    }

    fun updateAppLocale(locale: Locale?) = viewModelScope.launch(Dispatchers.IO) {
        locale?.language?.let { offlineUserInfoPreferencesRepository.setLanguage(it) }
    }
}