package com.gamovation.core.ui.level.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.clickableNoRipple
import com.gamovation.core.ui.common.ScalableButton
import kotlin.math.roundToInt

@Composable
fun LettersBlock(
    modifier: Modifier = Modifier,
    numberMaxLength: Int = 1,
    onAnswer: (String) -> Unit
) {
    var currentAnswer by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(Dimensions.Padding.Small.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
        ) {
            LettersBlockRowInput(
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

            LettersInputContent(
                onClickSymbol = { symbol ->
                    currentAnswer += if (currentAnswer.length < numberMaxLength) symbol else ""
                }
            )
        }
    }
}

@Composable
internal fun LettersBlockRowInput(
    currentAnswer: String,
    onAnswer: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
            delayOrder = 1
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
            delayOrder = 2
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
    alphabetType: AlphabetType = AlphabetType.Latin,
    onClickSymbol: (String) -> Unit
) {
    val currentAlphabet = when (alphabetType) {
        AlphabetType.Cyrillic -> defaultCyrillicAlphabet
        AlphabetType.Latin -> defaultLatinAlphabet
    }

    repeat((currentAlphabet.size / 7F).roundToInt()) { rowIndex ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
        ) {
            repeat(8) { itemIndex ->
                val text = currentAlphabet.getOrNull(rowIndex * 8 + itemIndex)

                text?.let {
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
                            modifier = Modifier
                                .padding(Dimensions.Padding.Small.value)
                                .clickableNoRipple(onClick = { onClickSymbol(text) }),
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
