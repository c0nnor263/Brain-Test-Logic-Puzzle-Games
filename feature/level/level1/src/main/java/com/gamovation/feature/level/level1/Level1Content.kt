package com.gamovation.feature.level.level1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.AnswersBlock

@Composable
fun Level1Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val listOfAnswers by remember { mutableStateOf(listOf(4, 8, 7, 6).shuffled()) }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            firstDuck,
            secondDuck,
            thirdDuck,
            fourthDuck,
            fifthDuck,
            sixthDuck,
            seventhDuck,
            eighthDuck,
            answerBlock
        ) = createRefs()

        val topHorizontalGuideline = createGuidelineFromTop(0.3f)
        val middleHorizontalGuideline = createGuidelineFromTop(0.6f)
        createHorizontalChain(firstDuck, secondDuck, thirdDuck, fourthDuck)
        createHorizontalChain(fifthDuck, sixthDuck, seventhDuck, eighthDuck)

        Level1Image(
            modifier = Modifier.constrainAs(firstDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topHorizontalGuideline)
            }
        )

        Level1Image(
            modifier = Modifier.constrainAs(secondDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topHorizontalGuideline)
            },
            index = 1
        )
        Level1Image(
            modifier = Modifier.constrainAs(thirdDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topHorizontalGuideline)
            },
            index = 2
        )
        Level1Image(
            modifier = Modifier.constrainAs(fourthDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topHorizontalGuideline)
            },
            index = 3
        )

        Level1Image(
            modifier = Modifier.constrainAs(fifthDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(firstDuck.bottom)
                bottom.linkTo(middleHorizontalGuideline)
            },
            index = 4
        )
        Level1Image(
            modifier = Modifier.constrainAs(sixthDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(firstDuck.bottom)
                bottom.linkTo(middleHorizontalGuideline)
            },
            index = 5
        )
        Level1Image(
            modifier = Modifier.constrainAs(seventhDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(firstDuck.bottom)
                bottom.linkTo(middleHorizontalGuideline)
            },
            index = 6
        )
        Level1Image(
            modifier = Modifier.constrainAs(eighthDuck) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(firstDuck.bottom)
                bottom.linkTo(middleHorizontalGuideline)
            },
            index = 7
        )

        AnswersBlock(
            modifier = Modifier.constrainAs(answerBlock) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(middleHorizontalGuideline)
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
            },
            listOfAnswers = listOfAnswers
        ) { answerText ->

            val duckCount = 8.toString()
            val state = if (answerText == duckCount) {
                val levelEventId = com.gamovation.core.domain.R.string.event_level_1_finished
                LevelScreenState.UserCorrectChoice(levelEventId)
            } else {
                val levelEventId = com.gamovation.core.domain.R.string.event_level_1_wrong
                LevelScreenState.UserWrongChoice(levelEventId)
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
