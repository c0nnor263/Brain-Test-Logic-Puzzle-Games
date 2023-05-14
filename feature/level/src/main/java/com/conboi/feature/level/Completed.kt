package com.conboi.feature.level

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.theme.WordefullTheme

@Composable
fun Completed(
    modifier: Modifier = Modifier,
    id: Int,
    onLevelUIAction: (LevelScreenState) -> Unit
) {

    AnimatedVisibility(
        true,
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
                    text = "Level $id completed",
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
                TextButton(onClick = {
                    onLevelUIAction(LevelScreenState.NEXT_LEVEL)
                }) {
                    Text("Next Level", style = MaterialTheme.typography.headlineMedium)
                }

                TextButton(onClick = {
                    // TODO : Add subscription

                }) {
                    Text("Buy subscription", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

    }
}


@Preview
@Composable
fun CompletedPreview() {
    WordefullTheme {
        Completed(id = 1, onLevelUIAction = {})
    }
}