package com.gamovation.core.ui.level.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ScalableButton

@Composable
fun LettersBlock(
    modifier: Modifier = Modifier,
    numberMaxLength: Int = 1,
    language: String,
    onAnswer: (String) -> Unit
) {
    var currentAnswer by rememberSaveable {
        mutableStateOf("")
    }

    val alphabetType = remember {
        determinateAlphabetType(language)
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            inputRow,
            alphabetInputFirstRow,
            alphabetInputSecondRow,
            alphabetInputThirdRow,
            alphabetInputFourthRow,
            alphabetInputFifthRow,
            alphabetInputSixthRow,
            alphabetInputSevenRow
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(0.3F)
        LettersBlockRowInput(
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
        Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

        val alphabetVerticalChain = createVerticalChain(
            alphabetInputFirstRow,
            alphabetInputSecondRow,
            alphabetInputThirdRow,
            alphabetInputFourthRow,
            alphabetInputFifthRow,
            alphabetInputSixthRow,
            alphabetInputSevenRow,
            chainStyle = ChainStyle.Packed
        )

        constrain(alphabetVerticalChain) {
            top.linkTo(topGuideline)
            bottom.linkTo(parent.bottom)
        }

        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputFirstRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 0,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )

        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputSecondRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 1,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputThirdRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 2,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputFourthRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 3,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputFifthRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 4,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputSixthRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 5,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
        LettersInputContent(
            modifier = Modifier.constrainAs(alphabetInputSevenRow) {
                width = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            rowIndex = 6,
            alphabetType = alphabetType,
            onClickSymbol = { symbol ->
                currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
            }
        )
    }
}

@Composable
internal fun LettersBlockRowInput(
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
                    contentDescription = null
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
internal fun LettersInputContent(
    modifier: Modifier = Modifier,
    rowIndex: Int,
    alphabetType: AlphabetType,
    onClickSymbol: (String) -> Unit
) {
    val currentAlphabet = remember {
        alphabetType.determinateAlphabetSymbols()
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(7) { itemIndex ->
            val text = currentAlphabet.getOrNull(rowIndex * 7 + itemIndex)

            text?.let {
                ScalableButton(
                    isDrawingAnimationEnabled = false,
                    onClick = {
                        onClickSymbol(text)
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
                            text = text,
                            modifier = Modifier.padding(Dimensions.Padding.Small.value),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

internal val defaultCyrillicAlphabet = listOf(
    "А",
    "Б",
    "В",
    "Г",
    "Д",
    "Е",
    "Ё",
    "Ж",
    "З",
    "И",
    "Й",
    "К",
    "Л",
    "М",
    "Н",
    "О",
    "П",
    "Р",
    "С",
    "Т",
    "У",
    "Ф",
    "Х",
    "Ц",
    "Ч",
    "Ш",
    "Щ",
    "Ъ",
    "Ы",
    "Ь",
    "Э",
    "Ю",
    "Я"
)

internal val defaultLatinAlphabet = listOf(
    "A",
    "B",
    "C",
    "D",
    "E",
    "F",
    "G",
    "H",
    "I",
    "J",
    "K",
    "L",
    "M",
    "N",
    "O",
    "P",
    "Q",
    "R",
    "S",
    "T",
    "U",
    "V",
    "W",
    "X",
    "Y",
    "Z"
)
