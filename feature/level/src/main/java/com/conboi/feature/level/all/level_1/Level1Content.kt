package com.conboi.feature.level.all.level_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.common.answers.AnswersBlock


@Composable
internal fun Level1Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val listOfAnswers by remember { mutableStateOf(listOf(4, 8, 7, 6).shuffled()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
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
                eighthDuck,
            ) = createRefs()


            Level1Image(
                modifier = Modifier.constrainAs(firstDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = -Dimensions.Padding.ExtraLarge2X.value)
                }
            )

            Level1Image(
                modifier = Modifier.constrainAs(secondDuck) {
                    top.linkTo(firstDuck.top, margin = Dimensions.Padding.ExtraLarge2X.value)
                    start.linkTo(firstDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                },
                index = 1
            )


            Level1Image(
                modifier = Modifier.constrainAs(thirdDuck) {
                    top.linkTo(secondDuck.top)
                    start.linkTo(secondDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                },
                index = 2
            )

            Level1Image(
                modifier = Modifier.constrainAs(fourthDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(secondDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                    bottom.linkTo(secondDuck.top, margin = Dimensions.Padding.Medium.value)
                }, index = 3
            )



            Level1Image(
                modifier = Modifier.constrainAs(fifthDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(fourthDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                },
                index = 4
            )



            Level1Image(
                modifier = Modifier.constrainAs(sixthDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(fifthDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                    bottom.linkTo(fifthDuck.bottom)
                }, index = 5
            )



            Level1Image(
                modifier = Modifier.constrainAs(seventhDuck) {
                    top.linkTo(parent.top)
                    start.linkTo(sixthDuck.end, margin = -Dimensions.Padding.ExtraLarge2X.value)
                    bottom.linkTo(thirdDuck.bottom)
                }, index = 6
            )


            Level1Image(
                modifier = Modifier.constrainAs(eighthDuck) {
                    start.linkTo(sixthDuck.end, margin = -Dimensions.Padding.ExtraLarge.value)
                    bottom.linkTo(parent.bottom)
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


