package com.conboi.feature.level.all.level_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.state.LocalLevelScreenState

@Composable
internal fun Level4Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val levelUIState = LocalLevelScreenState.current
    val bulbLambda = bulbLambda@{ isCorrectBulb: Boolean ->
        if (levelUIState != LevelScreenState.IS_PLAYING) return@bulbLambda null
        val newState = if (isCorrectBulb) {
            LevelScreenState.CORRECT_CHOICE
        } else {
            LevelScreenState.WRONG_CHOICE
        }
        onLevelAction(newState)
        isCorrectBulb
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.weight(0.5F),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                Level4Bulb(
                    modifier = Modifier.weight(0.3F),
                    index = index + 1,
                    onClick = bulbLambda
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.5F),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value),
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