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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun CounterBlock(
    modifier: Modifier = Modifier,
    onAnswer: (Int) -> Unit
) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value)
        ) {
            CounterBlockRowInput(
                count = count,
                onPlus = {
                    val newCount = count + 1
                    count = newCount.coerceAtMost(100)
                },
                onMinus = {
                    val newCount = count - 1
                    count = newCount.coerceAtLeast(0)
                }
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

            OptionButton(
                modifier = Modifier.padding(horizontal = Dimensions.Padding.Small.value),
                text = stringResource(R.string.submit),
                onClick = { onAnswer(count) },
                appearOrder = 0,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
internal fun CounterBlockRowInput(
    count: Int,
    onPlus: () -> Unit,
    onMinus: () -> Unit
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
            onClick = onPlus
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
                    text = "+",
                    modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        DrawAnimation(modifier = Modifier.weight(1F), appearOrder = 1) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    modifier = Modifier.padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        ScalableButton(
            modifier = Modifier
                .weight(1F)
                .wrapContentWidth(Alignment.Start),
            onClick = onMinus,
            appearOrder = 2
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
                    text = "-",
                    modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun CounterBlockPreview() {
    WordefullTheme {
        CounterBlock {
        }
    }
}
