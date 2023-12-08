package com.gamovation.feature.level.level4

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gamovation.core.ui.DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.clickableNoRipple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
internal fun Level4Bulb(
    modifier: Modifier = Modifier,
    index: Int,
    onClick: () -> Boolean?,
    onAnimationEnd: () -> Unit
) {
    @DrawableRes
    var bulbDrawableRes by remember { mutableIntStateOf(R.drawable.l4_lamp_off) }
    val painter = painterResource(id = bulbDrawableRes)

    val scope = rememberCoroutineScope()
    val bulbText by remember {
        mutableIntStateOf(
            index + 1 + Random.nextInt(0, 2)
        )
    }

    DrawAnimation(modifier = modifier, delayOrder = index) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = (if (bulbText == 4) 5 else index).toString(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Yellow,
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 32f,
                        offset = Offset(0F, 5F)
                    )
                )
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
            Image(
                modifier = Modifier
                    .clickableNoRipple {
                        if (onClick() == true) {
                            bulbDrawableRes = R.drawable.l4_lamp_on
                        } else {
                            scope.launch {
                                repeat(
                                    DEFAULT_LEVEL_SCREEN_COUNTDOWN_DURATION.toInt() /
                                        Durations.Short.time.times(2)
                                ) {
                                    bulbDrawableRes = R.drawable.l4_lamp_off
                                    delay(Durations.Short.time.toLong())
                                    bulbDrawableRes = R.drawable.l4_lamp_on
                                    delay(Durations.Short.time.toLong())
                                }
                                bulbDrawableRes = R.drawable.l4_lamp_off
                                onAnimationEnd()
                            }
                        }
                    },
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}
