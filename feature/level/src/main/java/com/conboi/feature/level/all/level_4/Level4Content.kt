package com.conboi.feature.level.all.level_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.feature.level.common.LocalLevelUIState

@Composable
internal fun Level4Content(modifier: Modifier = Modifier, onLevelAction: (LevelUIState) -> Unit) {
    val levelUIState = LocalLevelUIState.current
    val bulbLambda = bulbLambda@{ isCorrectBulb: Boolean ->
        if (levelUIState != LevelUIState.PROCESSING) return@bulbLambda null
        val newState = if (isCorrectBulb) {
            LevelUIState.COMPLETED
        } else {
            LevelUIState.FAILED
        }
        onLevelAction(newState)
        isCorrectBulb
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.weight(0.5F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                Level4Bulb(
                    modifier = Modifier.weight(0.33F),
                    index = index + 1,
                    onClick = bulbLambda
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.5F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(2) { index ->
                Level4Bulb(
                    modifier = Modifier.weight(0.5F),
                    index = index + 4,
                    onClick = bulbLambda
                )
            }
        }
    }
}


@Preview
@Composable
fun Level4ContentPreview() {
    Level4Content(onLevelAction = {})
}