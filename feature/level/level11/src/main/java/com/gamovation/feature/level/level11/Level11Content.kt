package com.gamovation.feature.level.level11

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.clickableNoRipple

@Composable
fun Level11Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Level11TopContent(
            onClick = {
                onLevelAction(LevelScreenState.USER_CORRECT_CHOICE)
            }
        )

        Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
        Level11BottomContent(
            onClick = {
                onLevelAction(LevelScreenState.USER_WRONG_CHOICE)
            }
        )
    }
}

@Composable
internal fun Level11TopContent(
    onClick: () -> Unit
) {
    var isCorrect by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(R.string.l11_what_are_the_names_of_baby),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.width(Dimensions.Padding.ExtraSmall.value))
        Text(
            text = stringResource(R.string.l11_cats),
            modifier = Modifier.clickable(onClick = {
                isCorrect = true
                onClick()
            }),
            style = MaterialTheme.typography.titleLarge,
            textDecoration = if (isCorrect) TextDecoration.Underline else TextDecoration.None
        )
    }
}

@Composable
fun Level11BottomContent(onClick: () -> Unit) {
    val listOfOptions = listOf(
        stringResource(R.string.l11_dog),
        stringResource(R.string.l11_mewka),
        stringResource(R.string.l11_murka),
        stringResource(R.string.l11_kilen),
        stringResource(R.string.l11_cow)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Medium.value)
    ) {
        listOfOptions.forEachIndexed { index, optionString ->
            DrawAnimation(delayOrder = index) {
                Card(
                    border = BorderStroke(4.dp, Color.White),
                    shape = Dimensions.RoundedShape.Large.value,
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(
                        text = optionString,
                        modifier = Modifier
                            .clickableNoRipple(onClick = onClick)
                            .padding(Dimensions.Padding.Small.value),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}
