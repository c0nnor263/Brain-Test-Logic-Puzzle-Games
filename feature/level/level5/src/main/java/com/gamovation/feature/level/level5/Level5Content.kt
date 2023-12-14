package com.gamovation.feature.level.level5

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
fun Level5Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val firstMarkInteractionSource = remember { MutableInteractionSource() }
    val secondMarkInteractionSource = remember { MutableInteractionSource() }

    val isFirstMarkPressed by firstMarkInteractionSource.collectIsPressedAsState()
    val isSecondMarkPressed by secondMarkInteractionSource.collectIsPressedAsState()

    LaunchedEffect(isFirstMarkPressed, isSecondMarkPressed) {
        val event = if (isFirstMarkPressed && isSecondMarkPressed) {
            LevelScreenState.UserCorrectChoice(
                com.gamovation.core.domain.R.string.event_level_5_finished
            )
        } else if (isFirstMarkPressed || isSecondMarkPressed) {
            LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_5_wrong
            )
        } else {
            return@LaunchedEffect
        }

        onLevelAction(event)
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            header, backgroundImage,

            firstMark,
            secondMark,
            thirdMark,

            fourthMark,
            fifthNark,
            sixthMark,

            seventhMark,
            eighthMark,
            ninthMark
        ) = createRefs()

        val topHeaderGuideline = createGuidelineFromTop(0.1F)

        val firstCellGuideline = createGuidelineFromTop(0.4F)
        val secondCellGuideline = createGuidelineFromTop(0.7F)

        Level5Header(
            modifier = Modifier.constrainAs(header) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                bottom.linkTo(topHeaderGuideline)
            }
        )

        DrawAnimation(
            modifier = Modifier.constrainAs(backgroundImage) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topHeaderGuideline)
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom)
            },
            appearOrder = 3
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.Padding.Small.value),
                painter = painterResource(
                    id = com.gamovation.feature.level.level5.R.drawable.l5_tic_tac_toe_board
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }


        val firstRowHorizontalChain = createHorizontalChain(
            firstMark,
            secondMark,
            thirdMark,
            chainStyle = ChainStyle.SpreadInside
        )

        constrain(firstRowHorizontalChain) {
            start.linkTo(parent.start, margin = Dimensions.Padding.Small.value)
            end.linkTo(parent.end, margin = Dimensions.Padding.Small.value)
        }

        val secondRowHorizontalChain = createHorizontalChain(
            fourthMark,
            fifthNark,
            sixthMark,
            chainStyle = ChainStyle.SpreadInside
        )

        constrain(secondRowHorizontalChain) {
            start.linkTo(parent.start, margin = Dimensions.Padding.Small.value)
            end.linkTo(parent.end, margin = Dimensions.Padding.Small.value)
        }

        val thirdRowHorizontalChain = createHorizontalChain(
            seventhMark,
            eighthMark,
            ninthMark,
            chainStyle = ChainStyle.SpreadInside
        )

        constrain(thirdRowHorizontalChain) {
            start.linkTo(parent.start, margin = Dimensions.Padding.Small.value)
            end.linkTo(parent.end, margin = Dimensions.Padding.Small.value)
        }


        // First Row
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(firstMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(topHeaderGuideline)
                    bottom.linkTo(firstCellGuideline)
                },
            drawableRes = R.drawable.l5_o_mark,
            appearOrder = 4
        )

        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(secondMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(topHeaderGuideline)
                    bottom.linkTo(firstCellGuideline)
                },
            drawableRes = R.drawable.l5_o_mark,
            appearOrder = 5
        )
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(thirdMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(topHeaderGuideline)
                    bottom.linkTo(firstCellGuideline)
                },
            interactionSource = firstMarkInteractionSource,
            alpha = if (isFirstMarkPressed) 1f else 0f,
            drawableRes = R.drawable.l5_x_mark,
            appearOrder = null
        )

        // Second Row
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(fourthMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(firstCellGuideline)
                    bottom.linkTo(secondCellGuideline)
                }
                .padding(Dimensions.Padding.Small.value),
            drawableRes = R.drawable.l5_x_mark,
            alpha = 0F,
            appearOrder = null
        )
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(fifthNark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(firstCellGuideline)
                    bottom.linkTo(secondCellGuideline)
                }
                .padding(Dimensions.Padding.Small.value),
            drawableRes = R.drawable.l5_x_mark,
            alpha = 0F,
            appearOrder = null
        )
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(sixthMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(firstCellGuideline)
                    bottom.linkTo(secondCellGuideline)
                }
                .padding(Dimensions.Padding.Small.value),
            drawableRes = R.drawable.l5_x_mark,
            appearOrder = 5
        )

        // Third Row
        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(seventhMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(secondCellGuideline)
                    bottom.linkTo(parent.bottom)
                },
            drawableRes = R.drawable.l5_o_mark,
            appearOrder = 6
        )

        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(eighthMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(secondCellGuideline)
                    bottom.linkTo(parent.bottom)
                },
            drawableRes = R.drawable.l5_o_mark,
            alpha = 0F,
            appearOrder = null
        )

        Level5TicTacToeCell(
            modifier = Modifier
                .constrainAs(ninthMark) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(secondCellGuideline)
                    bottom.linkTo(parent.bottom)
                },
            drawableRes = R.drawable.l5_x_mark,
            interactionSource = secondMarkInteractionSource,
            alpha = if (isSecondMarkPressed) 1f else 0f,
            appearOrder = null
        )
    }
}

@Preview
@Composable
fun Level5ContentPreview() {
    Level5Content(onLevelAction = {})
}
