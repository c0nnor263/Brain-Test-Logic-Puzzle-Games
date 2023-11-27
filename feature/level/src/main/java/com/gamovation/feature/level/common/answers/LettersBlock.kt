package com.gamovation.feature.level.common.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.feature.level.R
import kotlin.math.roundToInt

@Composable
fun LettersBlock(
    modifier: Modifier = Modifier,
    alphabetType: AlphabetType = AlphabetType.Latin,
    numberMaxLength: Int = 1,
    onAnswer: (String) -> Unit
) {
    var currentAnswer by rememberSaveable {
        mutableStateOf("")
    }

    val currentAlphabet = when (alphabetType) {
        AlphabetType.Cyrillic -> defaultCyrillicAlphabet
        AlphabetType.Latin -> defaultLatinAlphabet
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(Dimensions.Padding.Small.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(1F)
                        .wrapContentWidth(Alignment.End),
                    onClick = {
                        currentAnswer =
                            if (currentAnswer.length > 1) currentAnswer.dropLast(1) else ""
                    }
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.no_icon),
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .widthIn(min = 50.dp)
                        .weight(1F),
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

                IconButton(
                    modifier = Modifier
                        .weight(1F)
                        .wrapContentWidth(Alignment.Start),
                    onClick = {
                        if (currentAnswer.isNotBlank()) {
                            onAnswer(currentAnswer)
                        }
                    }
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.yes_icon),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

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
                                        .clickableNoRipple {
                                            currentAnswer += if (currentAnswer.length < numberMaxLength) text else ""
                                        },
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LettersBlockLatinPreview() {
    WordefullTheme {
        LettersBlock(alphabetType = AlphabetType.Latin) {
        }
    }
}

@Preview
@Composable
fun LettersBlockCyrillicPreview() {
    WordefullTheme {
        LettersBlock(alphabetType = AlphabetType.Cyrillic) {
        }
    }
}

val defaultCyrillicAlphabet = listOf(
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

val defaultLatinAlphabet = listOf(
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
