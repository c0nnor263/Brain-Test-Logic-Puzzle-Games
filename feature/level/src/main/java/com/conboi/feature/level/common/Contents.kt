package com.conboi.feature.level.common

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.DEFAULT_LEVEL_SCREEN_RESTART_DURATION
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.state.LocalLevelActionState
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.core.ui.state.rememberInterstitialAdViewState
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.Completed
import com.conboi.feature.level.action_bar.Restarting
import kotlinx.coroutines.delay

@Composable
fun Contents(
    modifier: Modifier = Modifier,
    level: LevelData,
    onLevelAction: (LevelActionState) -> Unit,
    onLevelScreenAction: (LevelScreenState) -> Unit
) {
    val screenState = LocalLevelScreenState.current
    val actionState = LocalLevelActionState.current
    val activity = LocalContext.current as ComponentActivity
    val interstitialAdView = rememberInterstitialAdViewState(activity = activity)

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
            return@Box
        }



        when (screenState) {
            LevelScreenState.COMPLETED -> {

                Completed(
                    id = level.id - 1, onLevelUIAction = onLevelScreenAction
                )
            }

            else -> LevelBody(level = level, onLevelScreenAction = { newState ->
                if (screenState != LevelScreenState.IS_PLAYING) return@LevelBody
                onLevelScreenAction(newState)
            })
        }


    }
}


@Preview
@Composable
fun LevelContentsPreview() {
    WordefullTheme {
        Contents(level = LevelData(id = 2), onLevelAction = {}, onLevelScreenAction = {})
    }
}