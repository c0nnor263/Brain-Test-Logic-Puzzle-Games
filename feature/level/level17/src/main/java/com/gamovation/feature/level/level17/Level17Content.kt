package com.gamovation.feature.level.level17

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.tweenMedium
import com.gamovation.core.ui.level.interactions.CollisionImage
import com.gamovation.core.ui.level.interactions.DraggableImage
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun Level17Content(
    modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit
) {
    var isBalloonPoppedOut by remember { mutableStateOf(false) }


    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (boy, girl, status, rings, balloon, present) = createRefs()

        val topGuideline = createGuidelineFromTop(0.2F)
        createHorizontalChain(boy, status, girl)
        DrawAnimation(modifier = Modifier.constrainAs(boy) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            centerVerticallyTo(status)
            centerHorizontallyTo(status)
        }) {
            Image(
                painter = painterResource(id = R.drawable.l17_boy),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(status) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topGuideline)
                centerHorizontallyTo(parent)
            }, appearOrder = 1
        ) {
            Text(
                text = if (isBalloonPoppedOut) {
                    stringResource(R.string.l17_heart_emoji)
                } else {
                    stringResource(
                        R.string.l17_snooze_emoji
                    )
                }, style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center

            )
        }
        DrawAnimation(
            modifier = Modifier.constrainAs(girl) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerVerticallyTo(status)
                centerHorizontallyTo(status)
            }, appearOrder = 2
        ) {
            Image(
                painter = painterResource(id = R.drawable.l17_girl),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }

        createHorizontalChain(
            rings, balloon, present
        )

        OptionsContent(isBalloonPoppedOut = isBalloonPoppedOut,
            rings = rings,
            balloon = balloon,
            present = present,
            onMatch = {
                isBalloonPoppedOut = true
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_17_finished
                    )
                )
            })
    }
}

@Composable
internal fun ConstraintLayoutScope.OptionsContent(
    isBalloonPoppedOut: Boolean,
    rings: ConstrainedLayoutReference,
    balloon: ConstrainedLayoutReference,
    present: ConstrainedLayoutReference,
    onMatch: () -> Unit
) {
    var ringsOffset by remember { mutableStateOf(Offset.Zero) }
    val balloonScaleAnimation by animateFloatAsState(
        targetValue = if (isBalloonPoppedOut) 0F else 1F, animationSpec = tweenMedium(), label = ""
    )

    val middleHorizontalGuideline = createGuidelineFromTop(0.35F)
    val bottomHorizontalGuideline = createGuidelineFromTop(0.5F)

    DrawAnimation(modifier = Modifier.constrainAs(rings) {
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(balloon.start)
        centerVerticallyTo(balloon)
    }) {
        DraggableImage(
            isEnabled = !isBalloonPoppedOut,
            drawableRes = R.drawable.l17_rings,

            ) { offset, _ ->
            ringsOffset = offset
        }
    }

    CollisionImage(modifier = Modifier
        .constrainAs(balloon) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(middleHorizontalGuideline)
            start.linkTo(rings.end)
            end.linkTo(present.start)
            bottom.linkTo(bottomHorizontalGuideline)
        }
        .scale(balloonScaleAnimation),
        defaultDrawableRes = R.drawable.l17_balloon,
        outerOffset = ringsOffset,
        appearOrder = 1,
        onMatch = onMatch)

    DrawAnimation(
        modifier = Modifier.constrainAs(present) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            end.linkTo(parent.end)
            start.linkTo(balloon.end)
            centerVerticallyTo(balloon)
        },
        appearOrder = 2
    ) {
        DraggableImage(
            isEnabled = !isBalloonPoppedOut,
            drawableRes = R.drawable.l17_present,
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
