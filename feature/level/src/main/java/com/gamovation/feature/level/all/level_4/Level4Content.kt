package com.gamovation.feature.level.all.level_4

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions

@Composable
internal fun Level4Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    var bulbAnimationStarted by remember {
        mutableStateOf(false)
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
                                    margin = Dimensions.Padding.Small.value
                                )
                            }
                        }
                    }
                    .padding(Dimensions.Padding.ExtraSmall.value),
                index = index,
                onClick = {
                    if (bulbAnimationStarted) return@Level4Bulb null

                    val isCorrectBulb = index == 3
                    val newState = if (isCorrectBulb) {
                        LevelScreenState.CORRECT_CHOICE
                    } else LevelScreenState.WRONG_CHOICE
                    onLevelAction(newState)
                    bulbAnimationStarted = true
                    isCorrectBulb
                },
                onAnimationEnd = {
                    bulbAnimationStarted = false
                }
            )
        }

    }
}


@Preview
@Composable
fun Level4ContentPreview() {
    Level4Content(onLevelAction = {})
}