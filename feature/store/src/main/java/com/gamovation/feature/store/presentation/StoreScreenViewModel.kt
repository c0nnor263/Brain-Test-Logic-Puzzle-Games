package com.gamovation.feature.store.presentation

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamovation.core.data.billing.BillingDataSource
import com.gamovation.core.data.billing.BillingProductType
import com.gamovation.core.data.model.StoreItemInfo
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.gamovation.core.domain.billing.UserVipType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class StoreScreenViewModel @Inject constructor(
    private val billingDataSource: BillingDataSource,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState.asStateFlow()

    fun getInAppProductsDetails() = billingDataSource.productsDetailsFlow
    fun purchaseProduct(
        storeItemInfo: StoreItemInfo,
        onError: () -> Unit,
        onRequestActivity: () -> ComponentActivity
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            val (details, type) = storeItemInfo
            if (details == null) return@launch

            val currentType = userInfoPreferencesRepository.getUserVipType().first()
            when (type) {
                BillingProductType.REMOVE_ADS, BillingProductType.VIP -> {
                    if (currentType != UserVipType.BASE) {
                        onError()
                    } else {
                        billingDataSource.purchaseProduct(details, type, onRequestActivity)
                    }
                }

                else -> billingDataSource.purchaseProduct(details, type, onRequestActivity)
            }
        }

    fun watchAdReward() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.buyCurrency(25)
    }

    fun updateUiState(uiState: UiState) {
        _uiState.value = uiState
    }

    fun clearUiState() {
        _uiState.value = null
    }

    sealed class UiState {
        data class OnBuy(val info: StoreItemInfo) : UiState()
        data object OnWatchAd : UiState()

        data object OnError : UiState()
    }
}
