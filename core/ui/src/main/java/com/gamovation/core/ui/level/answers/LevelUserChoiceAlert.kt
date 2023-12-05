package com.gamovation.core.ui.level.answers

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import java.util.concurrent.TimeUnit
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter

@Composable
fun BoxScope.LevelUserChoiceAlert(
    currentState: LevelScreenState,
    checkState: LevelScreenState
) {
    if (currentState != LevelScreenState.USER_CORRECT_CHOICE &&
        currentState != LevelScreenState.USER_WRONG_CHOICE
    ) {
        return
    }
    val isCorrectChoice = currentState == LevelScreenState.USER_CORRECT_CHOICE

    @DrawableRes val drawableRes = if (isCorrectChoice) {
        R.drawable.success
    } else {
        R.drawable.failure
    }
    val isVisible = currentState == checkState

    val backgroundOverlayColorAnimation by animateColorAsState(
        targetValue = if (isVisible) {
            Color.Black.copy(0.5F)
        } else {
            Color.Transparent
        },
        animationSpec = tweenMedium(delayMillis = if (isVisible) Durations.Medium.time else 0),
        label = "Background overlay color animation"
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundOverlayColorAnimation
        ) {
            ImageResult(isVisible = isVisible, drawableRes = drawableRes)
        }
    }

    AnimatedVisibility(
        isCorrectChoice,
        modifier = Modifier.align(Alignment.Center),
        enter = fadeIn(tweenMedium()),
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
internal fun ImageResult(isVisible: Boolean, @DrawableRes drawableRes: Int) {
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
                    painter = painterResource(id = drawableRes),
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
