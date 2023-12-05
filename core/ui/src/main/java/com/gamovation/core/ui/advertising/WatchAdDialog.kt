package com.gamovation.core.ui.advertising

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gamovation.core.ui.state.DialogState
import com.gamovation.core.ui.state.RewardedInterstitialAdViewState

@Composable
fun WatchAdDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onDismissed: (Boolean?) -> Unit,
    rewardedInterstitialAd: RewardedInterstitialAdViewState
) {
    val activity = LocalContext.current as ComponentActivity

    LaunchedEffect(dialogState.isShowing) {
        if (dialogState.isShowing) {
            rewardedInterstitialAd.showAd(activity) {
                if (it == false) {
                    dialogState.show()
                } else {
                    onDismissed(it)
                }
            }
        }
    }

    WatchAdErrorDialog(
        modifier = modifier,
        dialogState = dialogState,
        onDismissed = {
            dialogState.dismiss()
            onDismissed(it)
        }
    )
}
