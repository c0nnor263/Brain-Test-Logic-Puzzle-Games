package com.gamovation.core.ui.level.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ScalableButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun NumbersBlock(
    modifier: Modifier = Modifier,
    listOfAnswers: ImmutableList<Int> = persistentListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
    numberMaxLength: Int = 1,
    onAnswer: (String) -> Unit
) {
    var currentAnswer by rememberSaveable {
        mutableStateOf("")
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (inputRow, numberInputFirstRow, numberInputSecondRow) = createRefs()

        val topGuideline = createGuidelineFromTop(0.3F)
        NumbersBlockInputRow(
            modifier = Modifier.constrainAs(inputRow) {
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
                bottom.linkTo(topGuideline)
            },
            currentAnswer = currentAnswer,
            onAnswer = {
                if (currentAnswer.isNotBlank()) {
                    onAnswer(currentAnswer)
                }
            },
            onRemove = {
                currentAnswer =
                    if (currentAnswer.length > 1) currentAnswer.dropLast(1) else ""
            }
        )

        val alphabetVerticalChain = createVerticalChain(
            numberInputFirstRow,
            numberInputSecondRow,
            chainStyle = ChainStyle.Packed
        )
        constrain(alphabetVerticalChain) {
            top.linkTo(topGuideline)
            bottom.linkTo(parent.bottom)
        }

        NumberInputRowButtons(
            modifier = Modifier.constrainAs(numberInputFirstRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            listOfAnswers = listOfAnswers,
            itemAfterIndex = 0,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        NumberInputRowButtons(
            modifier = Modifier.constrainAs(numberInputSecondRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            listOfAnswers = listOfAnswers,
            itemAfterIndex = 5,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
    }
}

@Composable
internal fun NumbersBlockInputRow(
    modifier: Modifier = Modifier,
    currentAnswer: String,
    onAnswer: () -> Unit,
    onRemove: () -> Unit

) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScalableButton(
            modifier = Modifier
                .weight(1F)
                .wrapContentWidth(Alignment.End),
            onClick = onRemove
        ) {
            Image(
                modifier = Modifier.size(Dimensions.Padding.ExtraLarge.value),
                painter = painterResource(id = R.drawable.no_icon),
                contentDescription = null
            )
        }

        DrawAnimation(
            modifier = Modifier.weight(1F),
            appearOrder = 1
        ) {
            Box(
                modifier = Modifier.widthIn(min = 96.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.matchParentSize(),
                    painter = painterResource(id = R.drawable.answer_frame),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = currentAnswer,
                    modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        ScalableButton(
            modifier = Modifier
                .weight(1F)
                .wrapContentWidth(Alignment.Start),
            onClick = onAnswer,
            appearOrder = 2
        ) {
            Image(
                modifier = Modifier.size(Dimensions.Padding.ExtraLarge.value),
                painter = painterResource(id = R.drawable.yes_icon),
                contentDescription = null
            )
        }
    }
}

@Composable
fun NumberInputRowButtons(
    modifier: Modifier = Modifier,
    listOfAnswers: ImmutableList<Int>,
    itemAfterIndex: Int,
    onClickSymbol: (String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            Dimensions.Padding.ExtraSmall.value,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            val symbol = remember { listOfAnswers[it + itemAfterIndex].toString() }
            ScalableButton(

                isDrawingAnimationEnabled = false,
                onClick = {
                    onClickSymbol(symbol)
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.matchParentSize(),
                        painter = painterResource(id = R.drawable.answer_unit),
                        contentDescription = null
                    )

                    Text(
                        text = symbol,
                        modifier = Modifier
                            .padding(Dimensions.Padding.Medium.value),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}
