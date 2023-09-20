package com.gamovation.feature.store

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.android.billingclient.api.ProductDetails
import com.gamovation.core.data.billing.BillingProductType
import com.gamovation.core.data.billing.store.StoreScreenDetails
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState
import com.gamovation.core.ui.store.WatchAdDialog
import com.gamovation.core.ui.store.WatchStoreItem
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.feature.store.items.BestChoiceContent
import com.gamovation.feature.store.items.SmartestOfferContent
import com.gamovation.feature.store.items.StoreItem
import com.gamovation.feature.store.items.VipContent

@Composable
fun StoreScreen(
    storeDetails: StoreScreenDetails?,
    errorDialog: Boolean,
    onBuy: (ProductDetails, BillingProductType) -> Unit,
    onDismissErrorDialog: () -> Unit,
    onWatchAd: () -> Unit,
) {
    val context = LocalContext.current as ComponentActivity
    var showWatchAdDialog by remember { mutableStateOf(false) }
    val rewardedInterstitialAd = rememberRewardedInterstitialAdViewState(
        context,
        stringResource(id = com.gamovation.core.data.R.string.admob_rewarded_id_store_screen_watch_ad)
    )
    LazyColumn(
        modifier = Modifier
            .semantics { contentDescription = "StoreLazyColumn" }
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Dimensions.Padding.Small.value),
        verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Medium.value)
    ) {
        item {
            VipContent(onBuy = {
                storeDetails?.vipDetails?.let {
                    onBuy(it, BillingProductType.VIP)
                }
            })
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                SmartestOfferContent(
                    modifier = Modifier.weight(0.5F),
                    storeDetails?.smartestOfferDetails,
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
            WatchStoreItem(
                value = "x25",
                text = "Watch Ad\nfor reward",
                isLoaded = rewardedInterstitialAd.isAdLoaded,
                onClick = {
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

    WatchAdDialog(
        visible = showWatchAdDialog,
        rewardedInterstitialAd = rewardedInterstitialAd,
        onDismissed = { result ->
            Log.i("TAG", "StoreScreen: $result")
            showWatchAdDialog = false
            if (result == true) onWatchAd()
        })

    StoreScreenErrorDialog(
        visible = errorDialog,
        onDismiss = onDismissErrorDialog
    )
}

@Preview
@Composable
fun StoreScreenPreview() {
    WordefullTheme {
        StoreScreen(null, false, onWatchAd = {}, onDismissErrorDialog = {}, onBuy = { _, _ ->

        })
    }
}