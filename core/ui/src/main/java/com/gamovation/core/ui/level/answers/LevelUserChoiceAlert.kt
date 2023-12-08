package com.gamovation.core.ui.level.answers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.animation.tweenMedium
import com.gamovation.core.ui.theme.WordefullTheme
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun BoxScope.LevelUserChoiceAlert(
    currentState: LevelScreenState,
    checkState: LevelScreenState
) {
    val isCorrectChoice = currentState == LevelScreenState.USER_CORRECT_CHOICE
    val isVisible = currentState == checkState

    val backgroundBrushAlpha by animateFloatAsState(
        targetValue = if (isVisible) {
            0.4F
        } else {
            0.0F
        },
        animationSpec = tweenMedium(delayMillis = if (isVisible) Durations.Medium.time else 0),
        label = "Background brush alpha"
    )
    val backgroundBrush = Brush.radialGradient(
        listOf(
            Color.Black.copy(backgroundBrushAlpha),
            Color.Black.copy(backgroundBrushAlpha),
            Color.Transparent
        )
    )

    AnimatedVisibility(
        isVisible,
        modifier = Modifier.align(Alignment.Center),
        enter = fadeIn(tweenMedium()) + slideInVertically(tweenMedium()),
        exit = slideOutVertically(tweenMedium(delayMillis = Durations.Medium.time)) {
            it
        } + fadeOut(
            tweenMedium()
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBrush),
            contentAlignment = Alignment.Center
        ) {
            ImageResult(isVisible = isVisible, isCorrectChoice = isCorrectChoice)
        }
    }

    KonfettiGreeting(
        modifier = Modifier.align(Alignment.Center),
        isCorrectChoice = isCorrectChoice
    )
}

@Composable
fun KonfettiGreeting(modifier: Modifier = Modifier, isCorrectChoice: Boolean) {
    AnimatedVisibility(
        isCorrectChoice,
        modifier = modifier,
        enter = fadeIn(tweenMedium(delayMillis = Durations.Medium.time)),
        exit = fadeOut(tweenMedium())
    ) {
        val party = remember {
            listOf(
                Party(
                    angle = 250,
                    speed = 0f,
                    maxSpeed = 10f,
                    damping = 1f,

                    spread = 45,
                    colors = listOf(
                        0xfce18a,
                        0xff726d,
                        0xf4306d,
                        0xb48def
                    ),
                    position = Position.Relative(0.3, 1.0),
                    emitter = Emitter(
                        duration = Durations.ShortMedium.time.toLong(),
                        TimeUnit.MILLISECONDS
                    ).max(100)
                ),
                Party(
                    angle = 270,
                    speed = 0f,
                    maxSpeed = 10f,
                    damping = 1f,

                    spread = 45,
                    colors = listOf(
                        0xfce18a,
                        0xff726d,
                        0xf4306d,
                        0xb48def
                    ),
                    position = Position.Relative(0.5, 1.0),
                    emitter = Emitter(
                        duration = Durations.ShortMedium.time.toLong(),
                        TimeUnit.MILLISECONDS
                    ).max(100)
                ),
                Party(
                    angle = 290,
                    speed = 0f,
                    maxSpeed = 10f,
                    damping = 1f,

                    spread = 45,
                    colors = listOf(
                        0xfce18a,
                        0xff726d,
                        0xf4306d,
                        0xb48def
                    ),
                    position = Position.Relative(0.7, 1.0),
                    emitter = Emitter(
                        duration = Durations.ShortMedium.time.toLong(),
                        TimeUnit.MILLISECONDS
                    ).max(100)
                )
            )
        }
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = party
        )
    }
}

@Composable
internal fun ImageResult(isVisible: Boolean, isCorrectChoice: Boolean) {
    // Too remember the correct choice image and prevent showing the wrong choice image
    val drawableRes = remember {
        if (isCorrectChoice) {
            R.drawable.success
        } else {
            R.drawable.failure
        }
    }

    val haptic = LocalHapticFeedback.current

    LaunchedEffect(isCorrectChoice) {
        if (isCorrectChoice) {
            delay(Durations.Medium.time.toLong())
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            delay(Durations.Short.time.toLong())
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    val scaleAnimation by animateFloatAsState(
        targetValue = if (isVisible) {
            1.0F
        } else {
            0.8F
        },
        animationSpec = tweenMedium(delayMillis = Durations.Medium.time),
        label = "Scale animation"
    )

    Card(
        modifier = Modifier
            .wrapContentSize()
            .size(128.dp)
            .scale(scaleAnimation),
        shape = CircleShape
    ) {
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier.padding(Dimensions.Padding.Large.value),
            enter = fadeIn(tweenMedium(delayMillis = Durations.Medium.time)),
            exit = fadeOut(tweenMedium())
        ) {
            DrawAnimation(delayOrder = 1) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(
                        id = drawableRes
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Preview
@Composable
fun LevelUserChoiceAlertPreview() {
    WordefullTheme {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            LevelUserChoiceAlert(
                currentState = LevelScreenState.USER_WRONG_CHOICE,
                checkState = LevelScreenState.USER_WRONG_CHOICE
            )
        }
    }
}
