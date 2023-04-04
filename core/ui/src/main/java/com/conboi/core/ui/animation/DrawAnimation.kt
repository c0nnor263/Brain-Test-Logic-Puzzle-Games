package com.conboi.core.ui.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.DEFAULT_DRAW_ANIMATION_DURATION
import com.conboi.core.ui.R
import kotlin.random.Random

@Composable
fun DrawAnimation(state: Boolean, onClick: () -> Unit) {
    val transition = updateTransition(targetState = state, label = "")

    var randomY by remember { mutableStateOf(Random.nextInt(0, 60).toFloat() / 100) }
    LaunchedEffect(Unit) {
        randomY = Random.nextInt(0, 60).toFloat() / 100
    }

    val lineAnim1 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 4
            )

        },
        label = ""
    ) {
        if (it) 0f else 1f
    }
    val lineAnim2 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 3
            )

        },
        label = ""
    ) {
        if (it) 1f else 0f
    }

    val lineAnim3 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 2
            )

        },
        label = ""
    ) {
        if (it) 0f else 1f
    }

    val lineAnim4 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION
            )

        },
        label = ""
    ) {
        if (it) 1f else 0f
    }

    val lineAnim5 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing
            )

        },
        label = ""
    ) {
        if (it) 0f else 1f
    }


    val yAnim1 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 4
            )

        },
        label = ""
    ) {
        if (it) 0.06f else 0f
    }

    val yAnim2 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 3
            )

        },
        label = ""
    ) {
        if (it) 0.06f else 0f
    }

    val yAnim3 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 2
            )

        },
        label = ""
    ) {
        if (it) 0.06f else 0f
    }


    val yAnim4 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing, delayMillis = DEFAULT_DRAW_ANIMATION_DURATION
            )

        },
        label = ""
    ) {
        if (it) 0.06f else 0f
    }


    val yAnim5 by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION,
                easing = LinearEasing
            )

        },
        label = ""
    ) {
        if (it) 0.06f else 0f
    }

    val alphaAnim by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = DEFAULT_DRAW_ANIMATION_DURATION * 2,
                easing = LinearEasing,
                delayMillis = DEFAULT_DRAW_ANIMATION_DURATION * 5
            )

        },
        label = ""
    ) {
        if (it) 1f else 1f
    }
    Box(
        modifier = Modifier
            .size(256.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Yellow),
        )
        Canvas(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = 0.99F
            }) {
            drawRect(
                color = Color.White,
                size = size, blendMode = BlendMode.Src, alpha = alphaAnim
            )
            drawCanvasLine(index = 0, isRight = false, lineAnim5, yAnim5, randomY)
            drawCanvasLine(index = 1, isRight = true, lineAnim4, yAnim4, randomY)
            drawCanvasLine(index = 2, isRight = false, lineAnim3, yAnim3, randomY)
            drawCanvasLine(index = 3, isRight = true, lineAnim2, yAnim2, randomY)
            drawCanvasLine(index = 4, isRight = false, lineAnim1, yAnim1, randomY)

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DrawAnimationPreview() {
    var state by remember {
        mutableStateOf(false)
    }
    DrawAnimation(state) {
        state = !state
    }
}


fun DrawScope.drawCanvasLine(
    index: Int = 0,
    isRight: Boolean,
    animValue: Float,
    yAnim: Float,
    randomY: Float
) {
    return drawLine(
        color = Color.Transparent,
        start = if (isRight) {
            Offset(
                size.width * animValue,
                size.height * ((randomY + 0.28F + (0.06F * index)) - yAnim)
            )
        } else {
            Offset(0F, size.height * (randomY + 0.22F + (0.06F * index)))
        },
        end = if (isRight) {
            Offset(size.width, size.height * (randomY + 0.22F + (0.06F * index)))
        } else {
            Offset(
                size.width * animValue,
                size.height * ((randomY + 0.28F + (0.06F * index)) - yAnim)
            )
        },
        strokeWidth = 60.dp.toPx(),
        blendMode = BlendMode.Clear

    )
}