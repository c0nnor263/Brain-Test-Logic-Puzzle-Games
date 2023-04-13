package com.conboi.feature.level.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.feature.level.DEFAULT_LEVEL_UI_COUNTDOWN_DURATION
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LevelCompleted(modifier: Modifier = Modifier, onLevelUIAction: (LevelUIState) -> Unit) {

    var showAd by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = showAd) {
        // TODO Test ad
        if (showAd) {
            delay(DEFAULT_LEVEL_UI_COUNTDOWN_DURATION)
            onLevelUIAction(LevelUIState.FINISHED)
        }
    }
    AnimatedVisibility(
        !showAd,
        modifier = modifier

            .padding(Dimensions.Padding.Medium.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time)),
    ) {
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = Dimensions.RoundedShape.Medium.value
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick = { onLevelUIAction(LevelUIState.FINISHED) }) {
                    Text("Next Level")
                }

                TextButton(onClick = { showAd = true }) {
                    Text("Watch ad")
                }
            }

        }

    }

    AnimatedVisibility(
        showAd,
        modifier = modifier
            .size(128.dp)
            .padding(Dimensions.Padding.Medium.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time)),
    ) {
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = Dimensions.RoundedShape.Medium.value
        ) {
            Text(text = "Ad", style = MaterialTheme.typography.displayLarge)

        }

    }
}