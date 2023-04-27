package com.conboi.wordefull.presentation.navigation

import androidx.lifecycle.ViewModel
import com.conboi.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppContentViewModel @Inject constructor(
    private val offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) : ViewModel() {
    fun getUserCurrency() = offlineUserInfoPreferencesRepository.getUserCurrency()

}