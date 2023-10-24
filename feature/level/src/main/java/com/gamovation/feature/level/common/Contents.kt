package com.gamovation.feature.level.common

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.gamovation.core.data.R
import com.gamovation.core.database.data.LevelManager.Companion.DEFAULT_LEVEL_SCREEN_RESTART_DURATION
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.state.LocalLevelAction
import com.gamovation.core.ui.state.LocalLevelScreen
import com.gamovation.core.ui.state.rememberInterstitialAdViewState
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState
import com.gamovation.feature.level.Completed
import com.gamovation.feature.level.Final
import com.gamovation.feature.level.action_bar.Restarting
import kotlinx.coroutines.delay

@Composable
fun Contents(
    modifier: Modifier = Modifier,
    level: LevelData,
    onLevelAction: (LevelActionState) -> Unit,
    onLevelScreenAction: (LevelScreenState) -> Unit
) {
    val activity = LocalContext.current as ComponentActivity

    val screenState = LocalLevelScreen.current
    val actionState = LocalLevelAction.current
    val interstitialAdView = rememberInterstitialAdViewState(
        activity = activity,
        stringResource(id = R.string.admob_interstitial_id_level_completed)
    )
    val rewardedInterstitialAd = rememberRewardedInterstitialAdViewState(
        activity = activity,
        stringResource(id = R.string.admob_rewarded_id_level_completed_get_extra)
    )

    LaunchedEffect(actionState) {
        if (actionState == LevelActionState.RESTART) {
            delay(DEFAULT_LEVEL_SCREEN_RESTART_DURATION)
            onLevelAction(LevelActionState.IDLE)
        }
    }

    LaunchedEffect(screenState) {
        if (screenState == LevelScreenState.COMPLETED) {
            interstitialAdView.showAd(activity = activity)
        }
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.TopCenter
    ) {

        if (actionState == LevelActionState.RESTART) {
            Restarting(modifier = Modifier.fillMaxSize(), true)
        } else {
            when (screenState) {
                LevelScreenState.FINAL -> {
                    Final()
                }

                LevelScreenState.COMPLETED -> {
                    Completed(
                        id = level.id - 1,
                        rewardedInterstitialAd = rewardedInterstitialAd,
                        onLevelUIAction = onLevelScreenAction
                    )
                }

                else -> LevelBody(level = level, onLevelScreenAction = { newState ->

                    if (screenState != LevelScreenState.IS_PLAYING) return@LevelBody
                    onLevelScreenAction(newState)
                })
            }

        }
    }
}