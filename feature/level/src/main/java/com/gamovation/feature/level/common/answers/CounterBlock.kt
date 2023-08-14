package com.gamovation.feature.level.common.answers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.WordefullTheme


@Composable
fun CounterBlock(
    modifier: Modifier = Modifier,
    onAnswer: (Int) -> Unit
) {

    var count by rememberSaveable {
        mutableStateOf(0)
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


                Box(
                    modifier = Modifier
                        .weight(1F)
                        .wrapContentWidth(Alignment.End)
                        .clickableNoRipple {
                            val newCount = count + 1
                            count = newCount.coerceAtMost(100)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.matchParentSize(),
                        painter = painterResource(id = R.drawable.answer_unit),
                        contentDescription = null
                    )
                    Text(
                        text = "+",
                        modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }


                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = count.toString(),
                        modifier = Modifier.padding(Dimensions.Padding.Small.value),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1F)
                        .wrapContentWidth(Alignment.Start)
                        .clickableNoRipple {
                            val newCount = count - 1
                            count = newCount.coerceAtLeast(0)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.matchParentSize(),
                        painter = painterResource(id = R.drawable.answer_unit),
                        contentDescription = null
                    )
                    Text(
                        text = "-",
                        modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

            OptionButton(
                modifier = Modifier.padding(horizontal = Dimensions.Padding.ExtraLarge2X.value),
                index = 0,
                text = "Submit",
                onClick = { onAnswer(count) },
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}


@Preview
@Composable
fun CounterBlockPreview() {
    WordefullTheme {

        CounterBlock() {

        }

    }
}