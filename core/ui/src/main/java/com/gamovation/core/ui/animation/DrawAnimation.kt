package com.gamovation.core.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.R
import com.gamovation.core.ui.theme.WordefullTheme
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun DrawAnimation(
    modifier: Modifier = Modifier,
    delayOrder: Int? = 0,
    content: @Composable () -> Unit
) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var isStarted by remember { mutableStateOf(false) }
    var isFinished by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "Rotate chalk infinite transition")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 5F,
        targetValue = 30F,
        animationSpec = infiniteRepeatable(
            animation = tween(Durations.Short.time),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Rotate chalk animation"
    )
    val scaleAnimation by infiniteTransition.animateFloat(
        initialValue = 1F,
        targetValue = 1.2F,
        animationSpec = infiniteRepeatable(
            animation = tween(Durations.Short.time),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Scale chalk animation"

    )

    val alphaAnimation by animateFloatAsState(
        targetValue = if (isStarted) 1F else 0F,
        label = "Alpha chalk animation",
        animationSpec = if (delayOrder == null) snap() else tween(Durations.Medium.time)
    )

    LaunchedEffect(Unit) {
        launch {
            if (delayOrder != null) {
                val randomDelayTimeBeforeStart =
                    (Durations.Short.time..Durations.Medium.time).random().toLong()
                delay(delayOrder * randomDelayTimeBeforeStart)
            } else {
                isStarted = true
                isFinished = true
                return@launch
            }
            isStarted = true

            val offsetXJob = launch {
                repeat(3) { index ->
                    val xTargetState = when (index) {
                        0 -> 100F
                        1 -> -100F
                        2 -> 75F
                        else -> 0F
                    }

                    animate(
                        initialValue = offset.x,
                        targetValue = xTargetState,
                        animationSpec = tween(Durations.Short.time)
                    ) { current, _ ->
                        offset = Offset(current, offset.y)
                    }
                }
            }
            val offsetYJob = launch {
                repeat(3) { index ->
                    val yTargetState = when (index) {
                        0 -> 20F
                        1 -> -20F
                        2 -> 40F
                        else -> 0F
                    }
                    animate(
                        initialValue = offset.y,
                        targetValue = yTargetState,
                        animationSpec = tween(Durations.Short.time)
                    ) { current, _ ->
                        offset = Offset(offset.x, current)
                    }
                }
            }
            listOf(offsetXJob, offsetYJob).joinAll()
            isFinished = true
        }
    }

    BoxWithConstraints(
        modifier = modifier.alpha(alphaAnimation),
        contentAlignment = Alignment.Center
    ) {
        content()

        AnimatedVisibility(
            modifier = Modifier
                .size(24.dp)
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .rotate(rotateAnimation)
                .scale(scaleAnimation),
            visible = !isFinished,
            enter = scaleIn(animationSpec = tweenEasy()),
            exit = scaleOut(tweenMedium())
        ) {
            Image(
                painter = painterResource(id = R.drawable.chalk),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x0000000)
@Composable
fun DrawAnimationPreview() {
    WordefullTheme {
        DrawAnimation {
            Image(
                modifier = Modifier.size(128.dp),
                painter = painterResource(id = R.drawable.chalk),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}
