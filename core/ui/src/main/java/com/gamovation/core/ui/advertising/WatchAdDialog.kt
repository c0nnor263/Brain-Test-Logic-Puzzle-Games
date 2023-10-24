package com.gamovation.core.ui.advertising

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gamovation.core.ui.state.RewardedInterstitialAdViewState


@Composable
fun WatchAdDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissed: (Boolean?) -> Unit,
    rewardedInterstitialAd: RewardedInterstitialAdViewState,
) {
    val activity = LocalContext.current as ComponentActivity


    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            rewardedInterstitialAd.showAd(activity) {
                if (it == false) {
                    showErrorDialog = true
                } else {
                    onDismissed(it)
                }
            }
        }
    }

    WatchAdErrorDialog(
        modifier = modifier,
        visible = showErrorDialog,
        onDismissed = {
            showErrorDialog = false
            onDismissed(it)
        }
    )
}