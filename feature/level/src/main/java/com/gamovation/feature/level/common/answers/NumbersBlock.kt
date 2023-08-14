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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.WordefullTheme

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
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = currentAnswer,
                        modifier = Modifier.padding(Dimensions.Padding.Medium.value),
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
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.yes_icon),
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
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.matchParentSize(),
                            painter = painterResource(id = R.drawable.answer_unit),
                            contentDescription = null
                        )

                        Text(
                            text = listOfAnswers[it].toString(),
                            modifier = Modifier
                                .padding(Dimensions.Padding.Medium.value)
                                .clickableNoRipple {
                                    currentAnswer += if (currentAnswer.length < numberMaxLength) listOfAnswers[it].toString() else ""
                                },
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
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.matchParentSize(),
                            painter = painterResource(id = R.drawable.answer_unit),
                            contentDescription = null
                        )
                        Text(
                            text = listOfAnswers[it + 5].toString(),
                            modifier = Modifier
                                .padding(Dimensions.Padding.Medium.value)
                                .clickableNoRipple {
                                    currentAnswer += if (currentAnswer.length < numberMaxLength) listOfAnswers[it + 5].toString() else ""
                                },
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
fun NumbersBlockPreview() {
    WordefullTheme {

        NumbersBlock() {

        }

    }
}