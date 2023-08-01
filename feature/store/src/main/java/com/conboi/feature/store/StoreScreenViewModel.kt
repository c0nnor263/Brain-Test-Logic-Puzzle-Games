package com.conboi.feature.store

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.ProductDetails
import com.conboi.core.data.billing.BillingDataSource
import com.conboi.core.data.billing.BillingProductType
import com.conboi.core.data.repository.OfflineUserInfoPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreScreenViewModel @Inject constructor(
    private val billingDataSource: BillingDataSource,
    private val userInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) : ViewModel() {
    fun getInAppProductsDetails() = billingDataSource.productsDetailsFlow
    fun purchaseProduct(
        details: ProductDetails,
        type: BillingProductType,
        onRequestActivity: () -> ComponentActivity
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            billingDataSource.purchaseProduct(details, type, onRequestActivity)
        }

    fun watchAdReward() = viewModelScope.launch(Dispatchers.IO) {
        userInfoPreferencesRepository.buyCurrency(25)
    }

}