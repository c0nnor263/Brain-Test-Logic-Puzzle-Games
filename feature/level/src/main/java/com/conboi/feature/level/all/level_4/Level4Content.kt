package com.conboi.feature.level.all.level_4

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (bulb1, bulb2, bulb3, bulb4, bulb5) = createRefs()

        createHorizontalChain(
            bulb1, bulb2, bulb3
        )

        createHorizontalChain(
            bulb4, bulb5
        )

        listOf(
            bulb1,
            bulb2,
            bulb3,
            bulb4,
            bulb5
        ).forEachIndexed { index, reference ->
            Level4Bulb(
                modifier = Modifier
                    .constrainAs(reference) {
                        width = Dimension.preferredValue(128.dp)
                        when (index) {
                            in 0..2 -> {
                                top.linkTo(parent.top)
                            }

                            in 3..4 -> {
                                top.linkTo(
                                    bulb2.bottom,
                                    margin = Dimensions.Padding.Medium.value
                                )
                            }
                        }
                    }
                    .padding(Dimensions.Padding.Small.value),
                index = index + 1,
                onClick = bulbLambda
            )
        }

    }
}


@Preview
@Composable
fun Level4ContentPreview() {
    Level4Content(onLevelAction = {})
}