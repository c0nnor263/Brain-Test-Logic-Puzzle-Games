package com.gamovation.core.ui.advertising

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gamovation.core.domain.enums.RewardedInterstitialAdResult
import com.gamovation.core.ui.state.DialogState
import com.gamovation.core.ui.state.RewardedInterstitialAdViewState

@Composable
fun WatchAdDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onDismissed: (RewardedInterstitialAdResult) -> Unit,
    rewardedInterstitialAd: RewardedInterstitialAdViewState
) {
    val activity = LocalContext.current as ComponentActivity

    LaunchedEffect(dialogState.isShowing) {
        if (dialogState.isShowing) {
            rewardedInterstitialAd.showAd(activity) { result ->
                when (result) {
                    RewardedInterstitialAdResult.ERROR -> {
                        dialogState.show()
                    }

                    else -> onDismissed(result)
                }
            }
        }
    }

    WatchAdErrorDialog(
        modifier = modifier,
        dialogState = dialogState,
        onDismissed = {
            dialogState.dismiss()
            onDismissed(RewardedInterstitialAdResult.DISMISSED)
        }
    )
}
