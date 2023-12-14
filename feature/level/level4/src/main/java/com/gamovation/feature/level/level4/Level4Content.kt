package com.gamovation.feature.level.level4

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions

@Composable
fun Level4Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    var bulbAnimationStarted by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (bulb1, bulb2, bulb3, bulb4, bulb5) = createRefs()

        createHorizontalChain(
            bulb1,
            bulb2,
            bulb3
        )

        createHorizontalChain(
            bulb4,
            bulb5
        )

        val topGuideline = createGuidelineFromTop(0.5f)

        Level4Bulb(
            modifier = Modifier
                .constrainAs(bulb1) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(topGuideline)
                }
                .padding(Dimensions.Padding.ExtraSmall.value),
            index = 0,
            onClick = {
                if (bulbAnimationStarted) return@Level4Bulb null
                bulbAnimationStarted = true
                val newState =
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_4_wrong
                    )
                onLevelAction(newState)
                false
            },
            onAnimationEnd = {
                bulbAnimationStarted = false
            }
        )

        Level4Bulb(
            modifier = Modifier
                .constrainAs(bulb2) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(topGuideline)
                }
                .padding(Dimensions.Padding.ExtraSmall.value),
            index = 1,
            onClick = {
                if (bulbAnimationStarted) return@Level4Bulb null
                bulbAnimationStarted = true
                val newState =
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_4_wrong
                    )
                onLevelAction(newState)
                false
            },
            onAnimationEnd = {
                bulbAnimationStarted = false
            }
        )

        Level4Bulb(
            modifier = Modifier
                .constrainAs(bulb3) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(topGuideline)
                }
                .padding(Dimensions.Padding.ExtraSmall.value),
            index = 2,
            onClick = {
                if (bulbAnimationStarted) return@Level4Bulb null
                bulbAnimationStarted = true
                val newState =
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_4_wrong
                    )
                onLevelAction(newState)
                false
            },
            onAnimationEnd = {
                bulbAnimationStarted = false
            }
        )

        Level4Bulb(
            modifier = Modifier
                .constrainAs(bulb4) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(topGuideline)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .padding(Dimensions.Padding.ExtraSmall.value),
            index = 3,
            onClick = {
                if (bulbAnimationStarted) return@Level4Bulb null
                bulbAnimationStarted = true

                val newState =
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_4_finished
                    )
                onLevelAction(newState)
                true
            },
            onAnimationEnd = {
                bulbAnimationStarted = false
            }
        )

        Level4Bulb(
            modifier = Modifier
                .constrainAs(bulb5) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(topGuideline)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(Dimensions.Padding.ExtraSmall.value),
            index = 4,
            onClick = {
                if (bulbAnimationStarted) return@Level4Bulb null
                bulbAnimationStarted = true
                val newState =
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_4_wrong
                    )
                onLevelAction(newState)
                false
            },
            onAnimationEnd = {
                bulbAnimationStarted = false
            }
        )
    }
}

@Preview
@Composable
fun Level4ContentPreview() {
    Level4Content(onLevelAction = {})
}
