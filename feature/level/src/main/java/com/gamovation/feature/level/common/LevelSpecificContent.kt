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
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.DEFAULT_LEVEL_SCREEN_RESTART_DURATION
import com.gamovation.core.ui.state.LocalLevelAction
import com.gamovation.core.ui.state.LocalLevelScreen
import com.gamovation.core.ui.state.rememberInterstitialAdViewState
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState
import com.gamovation.feature.level.domain.model.LevelUiDetails
import com.gamovation.feature.level.uistate.CompetedTheGame
import com.gamovation.feature.level.uistate.LevelCompleted
import com.gamovation.feature.level.uistate.LevelRestart
import kotlinx.coroutines.delay

@Composable
internal fun LevelSpecificContent(
    modifier: Modifier = Modifier,
    details: LevelUiDetails,
    onReviewRequest: () -> Unit,
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
        if (screenState == LevelScreenState.LevelCompleted) {
            interstitialAdView.showAd(activity = activity)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        if (actionState == LevelActionState.RESTART) {
            LevelRestart(modifier = Modifier.fillMaxSize(), true)
        } else {
            when (screenState) {
                is LevelScreenState.CompletedTheGame -> {
                    CompetedTheGame()
                }

                LevelScreenState.LevelCompleted -> {
                    LevelCompleted(
                        id = details.id - 1,
                        rewardedInterstitialAd = rewardedInterstitialAd,
                        onLevelUIAction = onLevelScreenAction
                    )
                }

                else -> LevelBody(
                    details = details,
                    onReviewRequest = onReviewRequest,
                    onLevelScreenAction = { newState ->
                        if (screenState != LevelScreenState.IsLevelPlaying) return@LevelBody
                        onLevelScreenAction(newState)
                    }

                )
            }
        }
    }
}
