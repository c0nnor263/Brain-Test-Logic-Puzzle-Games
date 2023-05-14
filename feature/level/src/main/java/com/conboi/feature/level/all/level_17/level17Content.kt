package com.conboi.feature.level.all.level_17

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.common.interactions.CollisionImage
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level17Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    var isBalloonPoppedOut by remember { mutableStateOf(false) }
    var ringsOffset by remember { mutableStateOf(Offset.Zero) }
    val balloonScaleAnimation by animateFloatAsState(
        targetValue = if (isBalloonPoppedOut) 0F else 1F,
        label = ""
    )
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Medium.value)
    ) {
        val (boy,
            girl,
            status,
            rings,
            balloon,
            present
        ) = createRefs()

        createHorizontalChain(boy, status, girl)
        DrawAnimation(
            modifier = Modifier.constrainAs(boy) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
                top.linkTo(parent.top)
                start.linkTo(parent.start)

                centerVerticallyTo(status)
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.boy),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(status) {
                height = Dimension.ratio("1:1")
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
            },
            delayOrder = 1
        ) {
            Text(
                text = if (isBalloonPoppedOut) "❤️" else "\uD83D\uDCA4",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,

                )
        }
        DrawAnimation(modifier = Modifier.constrainAs(girl) {
            width = Dimension.fillToConstraints
            height = Dimension.ratio("1:1")
            top.linkTo(parent.top)
            end.linkTo(parent.end)

            centerVerticallyTo(status)
        }, delayOrder = 2) {

            Image(
                painter = painterResource(id = R.drawable.girl),

                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }



        createHorizontalChain(
            rings,
            balloon,
            present
        )



        DraggableImage(
            isEnabled = isBalloonPoppedOut.not(),
            drawableRes = R.drawable.rings,
            modifier = Modifier.constrainAs(rings) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
                centerVerticallyTo(balloon)
            },
            delayOrder = 3
        ) { offset, _ ->
            ringsOffset = offset
        }

        AnimatedVisibility(
            isBalloonPoppedOut.not(),
            modifier = Modifier.constrainAs(balloon) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
                top.linkTo(boy.bottom, margin = Dimensions.Padding.Medium.value)
            },
            exit = scaleOut(tween(Durations.Medium.time))
        ) {
            CollisionImage(
                modifier = Modifier.scale(balloonScaleAnimation),
                defaultDrawableRes = R.drawable.balloon,
                outerOffset = ringsOffset,
                delayOrder = 4
            ) {
                isBalloonPoppedOut = true
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            }

        }

        DraggableImage(
            isEnabled = isBalloonPoppedOut.not(),
            drawableRes = R.drawable.present,
            modifier = Modifier.constrainAs(present) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
                centerVerticallyTo(balloon)
            },
            delayOrder = 5
        )
    }
}


@Preview
@Composable
fun Level17ContentPreview() {
    WordefullTheme {
        Level17Content(onLevelAction = {})
    }
}