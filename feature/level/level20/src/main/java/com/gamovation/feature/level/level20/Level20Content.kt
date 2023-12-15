package com.gamovation.feature.level.level20

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.clickableNoRipple
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun Level20Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (dog, cat, penguin) = createRefs()

        createHorizontalChain(
            dog,
            cat,
            penguin
        )


        Level20Animal(
            reference = dog,
            drawableRes = R.drawable.l20_dog,
            appearOrder = 0,
            onClick = {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_20_wrong
                    )
                )
            }
        )

        Level20Animal(
            reference = cat,
            drawableRes = R.drawable.l20_cat,
            appearOrder = 1,
            onClick = {
                onLevelAction(
                    LevelScreenState.CompletedTheGame(
                        com.gamovation.core.domain.R.string.event_completed_the_game
                    )
                )
            }
        )

        Level20Animal(
            reference = penguin,
            drawableRes = R.drawable.l20_pengiun,
            appearOrder = 2,
            onClick = {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_20_wrong
                    )
                )
            }
        )
    }
}

@Composable
internal fun ConstraintLayoutScope.Level20Animal(
    reference: ConstrainedLayoutReference,
    appearOrder: Int,
    @DrawableRes drawableRes: Int,
    onClick: () -> Unit
) {
    val bottomGuideline = createGuidelineFromTop(
        0.3F
    )

    DrawAnimation(
        modifier = Modifier
            .constrainAs(reference) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = Dimensions.Padding.Medium.value)
                bottom.linkTo(bottomGuideline)
            }
            .clickableNoRipple(onClick = onClick),
        appearOrder = appearOrder
    ) {
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null,
        )
    }

}

@Preview
@Composable
fun Level20ContentPreview() {
    WordefullTheme {
        Level20Content(onLevelAction = {})
    }
}
