package com.conboi.feature.level.completed

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.conboi.core.domain.level.DEFAULT_LEVEL_UI_COUNTDOWN_DURATION
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import kotlinx.coroutines.delay

@Composable
fun LevelCompleted(
    modifier: Modifier = Modifier,
    levelId: Int,
    onLevelUIAction: (LevelScreenState) -> Unit
) {

    var showAd by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = showAd) {
        // TODO Test ad
        if (showAd) {
            delay(DEFAULT_LEVEL_UI_COUNTDOWN_DURATION)
            onLevelUIAction(LevelScreenState.NEXT_LEVEL)
        }
    }
    AnimatedVisibility(
        !showAd,
        modifier = modifier.padding(Dimensions.Padding.Medium.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time)),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lamp),
                modifier = Modifier.weight(0.25F),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Level $levelId completed",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "You awesome!",
                    style = MaterialTheme.typography.displaySmall.copy(color = Color.Yellow)
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = { onLevelUIAction(LevelScreenState.NEXT_LEVEL) }) {
                    Text("Next Level", style = MaterialTheme.typography.headlineMedium)
                }

                TextButton(onClick = { showAd = true }) {
                    Text("Watch ad", style = MaterialTheme.typography.headlineMedium)
                }

                TextButton(onClick = { showAd = true }) {
                    Text("Buy subscription", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

    }


    // TODO AD
    AnimatedVisibility(
        showAd,
        modifier = modifier.padding(Dimensions.Padding.Medium.value),
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