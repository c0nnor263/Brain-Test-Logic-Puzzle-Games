package com.gamovation.core.ui.level.answers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.ui.Dimensions

@Composable
fun AnswersBlock(
    modifier: Modifier = Modifier,
    listOfAnswers: List<Int>,
    onAnswer: (String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
    ) {
        repeat(2) { indexRow ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(2) { indexButton ->
                    val buttonIndex = indexRow * 2 + indexButton
                    val answerText = listOfAnswers[buttonIndex].toString()
                    OptionButton(
                        modifier = Modifier.weight(0.5F),
                        text = answerText,
                        delayOrder = buttonIndex,
                        onClick = { onAnswer(answerText) }
                    )
                }
            }
        }
    }
}
