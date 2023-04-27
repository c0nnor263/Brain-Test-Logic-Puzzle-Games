package com.conboi.feature.level.common.answers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R

@Composable
fun NumbersBlock(
    modifier: Modifier = Modifier,
    listOfAnswers: List<Int> = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
    numberMaxLength: Int = 1,
    onAnswer: (String) -> Unit
) {

    var currentAnswer by rememberSaveable {
        mutableStateOf("")
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
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
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_clear_24),
                        contentDescription = null
                    )
                }


                Card(
                    modifier = Modifier.widthIn(min = 50.dp),
                    border = BorderStroke(4.dp, Color.White),
                    shape = Dimensions.RoundedShape.Large.value,
                    colors = CardDefaults.cardColors(Color.LightGray.copy(alpha = 0.3F))
                ) {
                    Text(
                        text = currentAnswer,
                        modifier = Modifier.padding(
                            horizontal = Dimensions.Padding.Large.value,
                            vertical = Dimensions.Padding.Medium.value
                        ),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                IconButton(modifier = Modifier
                    .weight(1F)
                    .wrapContentWidth(Alignment.Start),
                    onClick = {
                        if (currentAnswer.isNotBlank()) {
                            onAnswer(currentAnswer)
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_check_24),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Dimensions.Padding.Small.value,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) {
                    Card(
                        shape = Dimensions.RoundedShape.Large.value,
                        colors = CardDefaults.cardColors(Color.Transparent)
                    ) {

                        Text(
                            text = listOfAnswers[it].toString(),
                            modifier = Modifier
                                .clickable {
                                    currentAnswer += if (currentAnswer.length < numberMaxLength) listOfAnswers[it].toString() else ""
                                }
                                .padding(
                                    vertical = Dimensions.Padding.Small.value,
                                    horizontal = Dimensions.Padding.Medium.value
                                ),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Dimensions.Padding.Small.value,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) {
                    Card(
                        shape = Dimensions.RoundedShape.Large.value,
                        colors = CardDefaults.cardColors(Color.Transparent)
                    ) {

                        Text(
                            text = listOfAnswers[it + 5].toString(),
                            modifier = Modifier
                                .clickable {
                                    currentAnswer += if (currentAnswer.length < numberMaxLength) listOfAnswers[it + 5].toString() else ""
                                }
                                .padding(
                                    vertical = Dimensions.Padding.Small.value,
                                    horizontal = Dimensions.Padding.Medium.value
                                ),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    NumbersBlock() {

    }

}