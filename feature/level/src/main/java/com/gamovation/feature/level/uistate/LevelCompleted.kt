package com.gamovation.feature.level.uistate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.domain.enums.RewardedInterstitialAdResult
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.advertising.WatchAdDialog
import com.gamovation.core.ui.advertising.WatchStoreButton
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.RewardedInterstitialAdViewState
import com.gamovation.core.ui.state.rememberDialogState
import com.gamovation.feature.level.common.Title

@Composable
internal fun LevelCompleted(
    modifier: Modifier = Modifier,
    id: Int,
    rewardedInterstitialAd: RewardedInterstitialAdViewState,
    onLevelUIAction: (LevelScreenState) -> Unit
) {
    val showWatchAdDialogState = rememberDialogState()

    LevelCompletedContent(
        modifier = modifier,
        id = id,
        isAdLoaded = rewardedInterstitialAd.isAdAvailable,
        onLevelUIAction = onLevelUIAction,
        onShowAd = {
            showWatchAdDialogState.show()
        }
    )

    WatchAdDialog(
        dialogState = showWatchAdDialogState,
        rewardedInterstitialAd = rewardedInterstitialAd,
        onDismissed = { result ->
            showWatchAdDialogState.dismiss()
            when (result) {
                RewardedInterstitialAdResult.REWARDED -> {
                    onLevelUIAction(
                        LevelScreenState.UserWatchAd(
                            com.gamovation.core.domain.R.string.event_level_competed_ad
                        )
                    )
                }

                else -> {}
            }
        }
    )
}

@Composable
fun LevelCompletedContent(
    modifier: Modifier = Modifier,
    id: Int,
    isAdLoaded: Boolean,
    onShowAd: () -> Unit,
    onLevelUIAction: (LevelScreenState) -> Unit
) {
    AnimatedVisibility(
        true,
        modifier = modifier.padding(Dimensions.Padding.Small.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lamp),
                modifier = Modifier.size(64.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            Title(
                text = stringResource(
                    id = com.gamovation.feature.level.R.string.level_completed,
                    id
                ),
                style = MaterialTheme.typography.displaySmall
            )
            Title(
                text = stringResource(
                    com.gamovation.feature.level.R.string.level_completed_you_awesome
                ),
                style = MaterialTheme.typography.displaySmall.copy(color = Color.Yellow)
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScalableButton(
                    onClick = {
                        onLevelUIAction(LevelScreenState.ProceedToNextLevel)
                    },
                    stringRes = com.gamovation.feature.level.R.string.next_level,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
                WatchStoreButton(
                    rewardStringRes = R.string.ad_reward,
                    isLoaded = isAdLoaded,
                    watchStringRes = R.string.watch_ad_for_reward,
                    onClick = onShowAd
                )
            }
        }
    }
}
