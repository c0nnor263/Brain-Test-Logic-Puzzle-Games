package com.conboi.feature.store

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.billingclient.api.ProductDetails
import com.conboi.core.data.billing.BillingProductType
import com.conboi.core.data.billing.store.StoreScreenDetails
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.store.WatchAdDialog
import com.conboi.core.ui.store.WatchStoreItem
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.store.items.BestChoiceContent
import com.conboi.feature.store.items.CoolestOfferContent
import com.conboi.feature.store.items.StoreItem
import com.conboi.feature.store.items.VipContent

@Composable
fun StoreScreen(
    storeDetails: StoreScreenDetails?,
    onBuy: (ProductDetails, BillingProductType) -> Unit,
    onWatchAd: (Boolean?) -> Unit
) {
    val scrollState = rememberScrollState()
    var showWatchAdDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = Dimensions.Padding.Small.value),
            verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Medium.value)
        ) {
            item {
                VipContent(onBuy = {
                    storeDetails?.vipDetails?.let { onBuy(it, BillingProductType.VIP) }
                })
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CoolestOfferContent(
                        modifier = Modifier.weight(0.5F),
                        storeDetails?.coolestOfferDetails,
                        onClick = onBuy
                    )
                    Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
                    BestChoiceContent(
                        modifier = Modifier.weight(0.5F),
                        storeDetails?.bestChoiceDetails,
                        onClick = onBuy
                    )
                }
            }

            item {
                StoreItem(value = "remove ads",
                    drawableRes = R.drawable.remove_ads,
                    details = storeDetails?.removeAdsDetails,
                    onClick = {
                        onBuy(it, BillingProductType.REMOVE_ADS)
                    })
            }
            item {
                WatchStoreItem(value = "x25", text = "watch", onClick = {
                    showWatchAdDialog = true
                })
            }
            item {
                StoreItem(value = "x250", details = storeDetails?.currency250Details, onClick = {
                    onBuy(it, BillingProductType.CURRENCY_250)
                })
            }
            item {
                StoreItem(value = "x500", details = storeDetails?.currency500Details, onClick = {
                    onBuy(it, BillingProductType.CURRENCY_500)
                })
            }
            item {
                StoreItem(value = "x1000", details = storeDetails?.currency1000Details, onClick = {
                    onBuy(it, BillingProductType.CURRENCY_1000)
                })
            }
        }
    }

    WatchAdDialog(visible = showWatchAdDialog, onDismissed = {
        showWatchAdDialog = false
        onWatchAd(it)
    })
}

@Preview
@Composable
fun StoreScreenPreview() {
    WordefullTheme {
        StoreScreen(null, onWatchAd = {}, onBuy = { _, _ ->

        })
    }
}