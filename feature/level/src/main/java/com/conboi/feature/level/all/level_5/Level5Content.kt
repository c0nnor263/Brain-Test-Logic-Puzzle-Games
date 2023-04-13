package com.conboi.feature.level.all.level_5

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation
import com.conboi.feature.level.common.LocalLevelUIState

@Composable
internal fun Level5Content(modifier: Modifier = Modifier, onLevelAction: (LevelUIState) -> Unit) {
    val levelUIState = LocalLevelUIState.current
    val firstMarkInteractionSource = remember { MutableInteractionSource() }
    val secondMarkInteractionSource = remember { MutableInteractionSource() }

    val isFirstMarkPressed by firstMarkInteractionSource.collectIsPressedAsState()
    val isSecondMarkPressed by secondMarkInteractionSource.collectIsPressedAsState()

    LaunchedEffect(isFirstMarkPressed, isSecondMarkPressed) {
        if (levelUIState != LevelUIState.PROCESSING) return@LaunchedEffect
        val event = if (isFirstMarkPressed && isSecondMarkPressed) {
            LevelUIState.COMPLETED
        } else if (isFirstMarkPressed || isSecondMarkPressed) {
            LevelUIState.FAILED
        } else {
            return@LaunchedEffect
        }
        onLevelAction(event)
    }


    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Level5Title()

        Spacer(modifier = Modifier.height(8.dp))
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)) {
            val (backgroundImage, firstMark, secondMark, thirdMark, fourthMark,
                fifthNark, sixthMark) = createRefs()


            DrawAnimation(
                modifier = Modifier.constrainAs(backgroundImage) {
                    top.linkTo(parent.top)
                    start.linkTo(firstMark.start)
                    end.linkTo(thirdMark.end)
                    bottom.linkTo(parent.bottom)
                }, delay = Durations.Medium.time.toLong() * 7) {
                Image(
                    modifier = Modifier.size(300.dp),
                    painter = painterResource(id = R.drawable.tic_tac_toe_board),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        firstMark
                    ) {
                        top.linkTo(parent.top)
                        end.linkTo(secondMark.start)
                    },
                drawableRes = R.drawable.o_mark,
                delay = 0L
            )

            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        secondMark
                    ) {

                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                drawableRes = R.drawable.o_mark,
                delay = Durations.Medium.time.toLong()
            )
            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        thirdMark
                    ) {
                        top.linkTo(parent.top)
                        start.linkTo(secondMark.end)
                    },
                interactionSource = firstMarkInteractionSource,
                alpha = if (isFirstMarkPressed) 1f else 0f,
                drawableRes = R.drawable.x_mark,
                delay = Durations.Medium.time.toLong() * 2
            )

            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        fourthMark
                    ) {
                        top.linkTo(thirdMark.bottom)
                        end.linkTo(thirdMark.end)
                        start.linkTo(thirdMark.start)
                        bottom.linkTo(sixthMark.top)

                    },
                drawableRes = R.drawable.x_mark,
                delay = Durations.Medium.time.toLong() * 3
            )

            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        fifthNark
                    ) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(firstMark.start)
                        end.linkTo(firstMark.end)
                    },
                drawableRes = R.drawable.o_mark,
                delay = Durations.Medium.time.toLong() * 4
            )


            Level5TicTacToeCell(
                modifier = Modifier
                    .padding(Dimensions.Padding.Small.value)
                    .constrainAs(
                        sixthMark
                    ) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(fourthMark.start)
                        end.linkTo(fourthMark.end)
                    },
                drawableRes = R.drawable.x_mark,
                interactionSource = secondMarkInteractionSource,
                alpha = if (isSecondMarkPressed) 1f else 0f,
                delay = Durations.Medium.time.toLong() * 5
            )

        }


    }
}


@Composable
fun Level5TicTacToeCell(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    interactionSource: MutableInteractionSource? = null,
    alpha: Float = 1F,
    delay: Long
) {
    val modifierWithInteractionSource = if (interactionSource != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {}
    } else Modifier

    DrawAnimation(modifier = modifier, delay = delay) {
        Image(
            modifier = Modifier
                .size(86.dp)
                .then(modifierWithInteractionSource),
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            alpha = alpha
        )
    }
}

@Preview
@Composable
fun Level5ContentPreview() {
    Level5Content(onLevelAction = {})
}