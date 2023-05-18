package com.conboi.feature.store

import androidx.lifecycle.ViewModel
import com.conboi.core.data.billing.BillingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreScreenViewModel @Inject constructor(
    private val billingDataSource: BillingDataSource
) : ViewModel() {
    fun getInAppProductsDetails() = billingDataSource.productsDetailsFlow

}