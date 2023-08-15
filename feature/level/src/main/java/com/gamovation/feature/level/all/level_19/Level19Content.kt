package com.gamovation.feature.level.all.level_19

import android.hardware.SensorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.gamovation.core.ui.R
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.feature.level.common.interactions.CollisionImage
import com.gamovation.feature.level.common.interactions.DraggableImage
import com.squareup.seismic.ShakeDetector

@Composable
fun Level19Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val context = LocalContext.current
    var bottleIsOpepened by remember {
        mutableStateOf(false)
    }

    var positionOfOpener by remember {
        mutableStateOf(Offset.Zero)
    }

    val scaleAnimation by animateFloatAsState(
        targetValue = if (bottleIsOpepened) 1.3f else 1f,
        animationSpec = repeatable(
            iterations = 10,
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "",
    ) {
        if (bottleIsOpepened) {
            onLevelAction(LevelScreenState.CORRECT_CHOICE)
        }
    }

    val rotateAnimation by animateFloatAsState(
        targetValue = if (bottleIsOpepened) -15F else 15f,
        animationSpec = repeatable(
            iterations = 10,
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ) {

    }

    LaunchedEffect(Unit) {
        val sensorManager = ContextCompat.getSystemService(
            context,
            SensorManager::class.java
        ) ?: return@LaunchedEffect
        ShakeDetector {
            bottleIsOpepened = true
        }.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
    }





    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (opener, bottle) = createRefs()

        createHorizontalChain(
            opener,
            bottle,
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
            outerOffset = positionOfOpener,
            defaultDrawableRes = R.drawable.l19_bottle,
            delayOrder = 1
        ) {
            onLevelAction(LevelScreenState.WRONG_CHOICE)
        }

        DraggableImage(
            modifier = Modifier.constrainAs(opener) {
                width = Dimension.fillToConstraints
                centerVerticallyTo(parent)
            },
            drawableRes = R.drawable.l19_opener,
        ) { offset, _ ->
            positionOfOpener = offset
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