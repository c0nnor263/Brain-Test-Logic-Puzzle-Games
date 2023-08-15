package com.gamovation.core.ui.store

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState


@Composable
fun WatchAdDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    adUnitID:String,
    onDismissed: (Boolean?) -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity
    val rewardedInterstitialAd = rememberRewardedInterstitialAdViewState(activity,adUnitID)

    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if(visible) {
            rewardedInterstitialAd.showAd(activity) {
                if (it == false) {
                    showErrorDialog = true
                } else {
                    onDismissed(it)
                }
            }
        }
    }

    WatchAdErrorDialog(visible = showErrorDialog, onDismissed = {
        showErrorDialog = false
        onDismissed(it)
    })
}