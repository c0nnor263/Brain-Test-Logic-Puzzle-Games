package com.gamovation.feature.store.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.billing.model.StoreItemInfo
import com.gamovation.core.billing.store.StoreScreenDetails
import com.gamovation.core.domain.enums.RewardedInterstitialAdResult
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.advertising.WatchAdDialog
import com.gamovation.core.ui.advertising.WatchStoreButton
import com.gamovation.core.ui.state.rememberDialogState
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState
import com.gamovation.feature.store.R
import com.gamovation.feature.store.domain.model.StoreListItem
import com.gamovation.feature.store.items.BestChoiceContent
import com.gamovation.feature.store.items.SmartestOfferContent
import com.gamovation.feature.store.items.StoreItem
import com.gamovation.feature.store.items.VipContent
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun StoreScreen(viewModel: StoreScreenViewModel) {
    val activity = LocalContext.current as ComponentActivity

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val errorDialogState = rememberDialogState()
    val showWatchAdDialogState = rememberDialogState()

    val rewardedInterstitialAd = rememberRewardedInterstitialAdViewState(
        activity,
        stringResource(
            id = com.gamovation.core.data.R.string.admob_rewarded_id_store_screen_watch_ad
        )
    )

    val productDetailsInfo by viewModel.getInAppProductsDetails().collectAsStateWithLifecycle()

    val storeDetails = remember(productDetailsInfo) {
        StoreScreenDetails.create(productDetailsInfo)
    }

    val currencyItems = remember(storeDetails) {
        persistentListOf(
            StoreListItem(
                stringRes = R.string.remove_ads,
                drawableRes = R.drawable.remove_ads,
                info = storeDetails?.removeAdsDetails
            ),
            StoreListItem(
                stringRes = R.string.x25,
                drawableRes = com.gamovation.core.ui.R.drawable.present,
                info = null
            ),
            StoreListItem(
                stringRes = R.string.x250,
                drawableRes = com.gamovation.core.ui.R.drawable.lamp,
                info = storeDetails?.currency250Details
            ),
            StoreListItem(
                stringRes = R.string.x500,
                drawableRes = com.gamovation.core.ui.R.drawable.lamp,
                info = storeDetails?.currency500Details
            ),
            StoreListItem(
                stringRes = R.string.x1000,
                drawableRes = com.gamovation.core.ui.R.drawable.lamp,
                info = storeDetails?.currency1000Details
            )
        )
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is StoreScreenViewModel.UiState.OnBuy -> {
                viewModel.purchaseProduct(
                    storeItemInfo = state.info,
                    onError = { errorDialogState.show() },
                    onRequestActivity = {
                        activity
                    }
                )
                viewModel.clearUiState()
            }

            is StoreScreenViewModel.UiState.OnWatchAd -> {
                viewModel.watchAdReward()
                viewModel.clearUiState()
            }

            StoreScreenViewModel.UiState.OnError -> {
                errorDialogState.show()
                viewModel.clearUiState()
            }

            null -> {}
        }
    }

    LazyColumn(
        modifier = Modifier
            .semantics { contentDescription = "StoreLazyColumn" }
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Dimensions.Padding.Small.value),
        verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
    ) {
        TopOfferContent(
            storeDetails = storeDetails,
            onBuy = { info ->
                val state = StoreScreenViewModel.UiState.OnBuy(info)
                viewModel.updateUiState(state)
            }
        )

        CurrencyContent(
            currencyItems = currencyItems,
            isAdLoaded = rewardedInterstitialAd.isAdAvailable,
            onShowAd = {
                showWatchAdDialogState.show()
            },
            onBuy = { info ->
                val state = StoreScreenViewModel.UiState.OnBuy(info)
                viewModel.updateUiState(state)
            }
        )
    }

    WatchAdDialog(
        dialogState = showWatchAdDialogState,
        rewardedInterstitialAd = rewardedInterstitialAd,
        onDismissed = { result ->
            showWatchAdDialogState.dismiss()
            when (result) {
                RewardedInterstitialAdResult.REWARDED -> {
                    viewModel.updateUiState(StoreScreenViewModel.UiState.OnWatchAd)
                }

                else -> {}
            }
        }
    )

    StoreScreenErrorDialog(
        dialogState = errorDialogState,
        onDismiss = errorDialogState::dismiss
    )
}

fun LazyListScope.CurrencyContent(
    currencyItems: PersistentList<StoreListItem>,
    isAdLoaded: Boolean,
    onShowAd: () -> Unit,
    onBuy: (StoreItemInfo) -> Unit
) {
    currencyItems.forEach { storeItem ->
        item {
            if (storeItem.info == null) {
                WatchStoreButton(
                    rewardStringRes = R.string.x25,
                    watchStringRes = com.gamovation.core.ui.R.string.watch_ad_for_reward,
                    isLoaded = isAdLoaded,
                    onClick = onShowAd
                )
            } else {
                StoreItem(
                    stringRes = storeItem.stringRes,
                    drawableRes = storeItem.drawableRes,
                    info = storeItem.info,
                    onBuy = onBuy
                )
            }
        }
    }
}

fun LazyListScope.TopOfferContent(
    storeDetails: StoreScreenDetails?,
    onBuy: (StoreItemInfo) -> Unit
) {
    item {
        VipContent(
            info = storeDetails?.vipDetails,
            onBuy = onBuy
        )
    }
    item {
        OfferContent(
            storeDetails = storeDetails,
            onBuy = onBuy
        )
    }
}

@Composable
fun OfferContent(
    storeDetails: StoreScreenDetails?,
    onBuy: (StoreItemInfo) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SmartestOfferContent(
            modifier = Modifier.weight(0.5F),
            info = storeDetails?.smartestOfferDetails,
            onBuy = onBuy
        )
        Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))

        BestChoiceContent(
            modifier = Modifier.weight(0.5F),
            info = storeDetails?.bestChoiceDetails,
            onBuy = onBuy
        )
    }
}
