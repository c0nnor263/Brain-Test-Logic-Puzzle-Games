package com.gamovation.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.animation.tweenMedium
import kotlinx.coroutines.delay

@Composable
fun BoxWithSparkles(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        content()
        repeat(3) {
            StarParticle(index = it)
        }
    }
}

@Composable
fun StarParticle(index: Int = 0) {
    val starParticleVisibleState = remember { MutableTransitionState(false) }
    var starParticleOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scaleAnimation by infiniteTransition.animateFloat(
        initialValue = 0.9F,
        targetValue = 1.1F,
        animationSpec = infiniteRepeatable(
            animation = tweenMedium(),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Scale Animation"
    )

    LaunchedEffect(Unit) {
        val indexDelay = 2000 * index.toLong()
        delay(indexDelay)
        while (true) {
            starParticleVisibleState.targetState = true
            delay(2000 + indexDelay)
            starParticleVisibleState.targetState = false
            delay(2000)
            val randomX = (-100..100).random()
            val randomY = (-100..100).random()

            starParticleOffset = Offset(
                x = randomX.toFloat(),
                y = randomY.toFloat()
            )
        }
    }

    AnimatedVisibility(
        visibleState = starParticleVisibleState,
        enter = scaleIn(tweenMedium()),
        exit = scaleOut(tweenMedium()),
        modifier = Modifier
            .absoluteOffset {
                IntOffset(
                    starParticleOffset.x.toInt(),
                    starParticleOffset.y.toInt()
                )
            }
            .scale(scaleAnimation)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.sparkle_star),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Yellow),
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview
@Composable
fun StarParticlePreview() {
    StarParticle()
}
