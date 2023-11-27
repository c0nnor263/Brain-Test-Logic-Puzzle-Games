package com.gamovation.feature.level.all.level1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.feature.level.common.answers.AnswersBlock

@Composable
internal fun Level1Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val listOfAnswers by remember { mutableStateOf(listOf(4, 8, 7, 6).shuffled()) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (
                firstDuck,
                secondDuck,
                thirdDuck,
                fourthDuck,
                fifthDuck,
                sixthDuck,
                seventhDuck,
                eighthDuck
            ) = createRefs()

            createHorizontalChain(firstDuck, secondDuck, thirdDuck)
            Level1Image(
                modifier = Modifier.constrainAs(firstDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = -Dimensions.Padding.Small.value)
                }
            )

            Level1Image(
                modifier = Modifier.constrainAs(secondDuck) {
                },
                index = 1
            )

            Level1Image(
                modifier = Modifier.constrainAs(thirdDuck) {
                },
                index = 2
            )

            createVerticalChain(fourthDuck, fifthDuck)
            Level1Image(
                modifier = Modifier.constrainAs(fourthDuck) {
                    top.linkTo(firstDuck.bottom)
                    start.linkTo(firstDuck.end, margin = Dimensions.Padding.ExtraLarge.value)
                },
                index = 3
            )

            Level1Image(
                modifier = Modifier.constrainAs(fifthDuck) {
                    start.linkTo(secondDuck.end, margin = Dimensions.Padding.ExtraLarge.value)
                },
                index = 4
            )

            createVerticalChain(sixthDuck, seventhDuck)
            Level1Image(
                modifier = Modifier.constrainAs(sixthDuck) {
                    bottom.linkTo(eighthDuck.top)
                    start.linkTo(secondDuck.end, margin = Dimensions.Padding.ExtraLarge.value)
                },
                index = 5
            )
            Level1Image(
                modifier = Modifier.constrainAs(seventhDuck) {
                },
                index = 6
            )

            Level1Image(
                modifier = Modifier.constrainAs(eighthDuck) {
                    top.linkTo(secondDuck.bottom)
                    start.linkTo(firstDuck.end, margin = Dimensions.Padding.ExtraLarge.value)
                },
                index = 7
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
        AnswersBlock(listOfAnswers = listOfAnswers) { answerText ->

            val duckCount = 8.toString()
            val state = if (answerText == duckCount) {
                LevelScreenState.CORRECT_CHOICE
            } else {
                LevelScreenState.WRONG_CHOICE
            }
            onLevelAction(state)
        }
    }
}

@Preview
@Composable
fun Level0ContentPreview() {
    Level1Content(onLevelAction = {})
}
