package com.gamovation.feature.level.level19

import android.hardware.SensorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.level.interactions.CollisionImage
import com.gamovation.core.ui.level.interactions.DraggableImage
import com.gamovation.core.ui.theme.WordefullTheme
import com.squareup.seismic.ShakeDetector

@Composable
fun Level19Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val context = LocalContext.current
    var bottleIsOpened by remember {
        mutableStateOf(false)
    }

    var openerPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    val scaleAnimation by animateFloatAsState(
        targetValue = if (bottleIsOpened) 1.3f else 1f,
        animationSpec = repeatable(
            iterations = 10,
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ) {
        if (bottleIsOpened) {
            onLevelAction(
                LevelScreenState.UserCorrectChoice(
                    com.gamovation.core.domain.R.string.event_level_19_finished
                )
            )
        }
    }

    val rotateAnimation by animateFloatAsState(
        targetValue = if (bottleIsOpened) -15F else 15f,
        animationSpec = repeatable(
            iterations = 10,
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ) {
    }

    LaunchedEffect(Unit) {
        val sensorManager = ContextCompat.getSystemService(
            context,
            SensorManager::class.java
        ) ?: return@LaunchedEffect
        ShakeDetector {
            bottleIsOpened = true
        }.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
    }

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (opener, bottle) = createRefs()

        createHorizontalChain(
            opener,
            bottle
        )

        CollisionImage(
            modifier = Modifier
                .constrainAs(bottle) {
                    scaleX = scaleAnimation
                    scaleY = scaleAnimation
                    rotationZ = rotateAnimation
                    width = Dimension.fillToConstraints
                    centerVerticallyTo(parent)
                },
            outerOffset = openerPosition,
            defaultDrawableRes = R.drawable.l19_bottle,
            appearOrder = 0
        ) {
            onLevelAction(
                LevelScreenState.UserWrongChoice(
                    com.gamovation.core.domain.R.string.event_level_19_wrong
                )
            )
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(opener) {
                width = Dimension.fillToConstraints
                centerVerticallyTo(parent)
            },
            appearOrder = 1
        ) {
            DraggableImage(
                drawableRes = R.drawable.l19_opener
            ) { offset, _ ->
                openerPosition = offset
            }
        }
    }
}

@Preview
@Composable
fun Level19ContentPreview() {
    WordefullTheme {
        Level19Content(onLevelAction = {})
    }
}
