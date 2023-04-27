package com.conboi.feature.level.all.level_4

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import com.conboi.core.domain.level.DEFAULT_LEVEL_UI_COUNTDOWN_DURATION
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Level4Bulb(
    modifier: Modifier = Modifier,
    index: Int,
    onClick: (Boolean) -> Boolean?
) {
    val interactionSource = remember { MutableInteractionSource() }

    @DrawableRes
    var bulbDrawableRes by remember { mutableStateOf(R.drawable.lamp_off) }
    val painter = painterResource(id = bulbDrawableRes)

    val scope = rememberCoroutineScope()


    DrawAnimation(modifier = modifier, delay = Durations.Medium.time.toLong() * index) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = index.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.Yellow, shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 32f,
                        offset = Offset(0F, 5F)
                    )
                )
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
            Image(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            val result = onClick(index == 4) ?: return@clickable
                            if (!result) {
                                scope.launch {
                                    repeat(
                                        DEFAULT_LEVEL_UI_COUNTDOWN_DURATION.toInt() /
                                                Durations.Short.time.times(2)
                                    ) {
                                        bulbDrawableRes = R.drawable.lamp_off
                                        delay(Durations.Short.time.toLong())
                                        bulbDrawableRes = R.drawable.lamp_on
                                        delay(Durations.Short.time.toLong())
                                    }
                                    bulbDrawableRes = R.drawable.lamp_off
                                }
                            } else {
                                bulbDrawableRes = R.drawable.lamp_on
                            }
                        }
                    ),
                painter = painter,
                contentDescription = null,
            )

        }
    }
}