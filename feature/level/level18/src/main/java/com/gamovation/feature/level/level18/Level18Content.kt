package com.gamovation.feature.level.level18

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.level.answers.CounterBlock
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun Level18Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            tShirtBlock,
            counterBlock
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(0.4F)

        TShirtBlock(tShirtBlock, topGuideline)

        CounterBlock(
            modifier = Modifier.constrainAs(counterBlock) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topGuideline, margin = Dimensions.Padding.Medium.value)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            if (it == 5) {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_18_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_18_wrong
                    )
                )
            }
        }
    }
}

@Composable
fun ConstraintLayoutScope.TShirtBlock(
    reference: ConstrainedLayoutReference,
    topGuideline: ConstraintLayoutBaseScope.HorizontalAnchor
) {
    ConstraintLayout(
        modifier = Modifier.constrainAs(reference) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(topGuideline)
        }
    ) {
        val (
            tShirt,
            spot1,
            spot2,
            spot3,
            spot4,
            spot5,
        ) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.l18_t_shirt),
            modifier = Modifier.constrainAs(tShirt) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom)
            },
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.l18_spot),
            modifier = Modifier.constrainAs(spot1) {
                rotationZ = 120f
                scaleY = 0.5F
                top.linkTo(tShirt.top, margin = Dimensions.Padding.Small.value)
                start.linkTo(tShirt.start)
                end.linkTo(tShirt.end, margin = Dimensions.Padding.ExtraLarge2X.value)
            },
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.l18_spot),
            modifier = Modifier.constrainAs(spot2) {
                rotationZ = 40f
                scaleX = 0.6F
                scaleY = 0.6F
                top.linkTo(spot1.bottom)
                end.linkTo(tShirt.end, margin = Dimensions.Padding.Small.value)
            },
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.l18_spot),
            modifier = Modifier.constrainAs(spot3) {
                rotationZ = 220f
                scaleX = 0.3F
                scaleY = 0.3F
                top.linkTo(spot2.bottom)
                start.linkTo(tShirt.start)
                end.linkTo(tShirt.end, margin = Dimensions.Padding.Small.value)
            },
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.l18_spot),
            modifier = Modifier.constrainAs(spot4) {
                rotationZ = 170f
                scaleX = 0.8F
                scaleY = 0.8F
                top.linkTo(spot3.bottom)
                start.linkTo(tShirt.start)
                end.linkTo(tShirt.end, margin = Dimensions.Padding.ExtraLarge.value)
            },
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.l18_spot),
            modifier = Modifier.constrainAs(spot5) {
                scaleX = 0.4F
                scaleY = 0.4F
                top.linkTo(spot4.bottom, margin = -Dimensions.Padding.Medium.value)
                start.linkTo(tShirt.start, margin = Dimensions.Padding.ExtraLarge.value)
                end.linkTo(tShirt.end)
            },
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun Level18ContentPreview() {
    WordefullTheme {
        Level18Content(onLevelAction = {})
    }
}
