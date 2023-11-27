package com.gamovation.feature.level.all.level18

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.feature.level.R
import com.gamovation.feature.level.common.answers.CounterBlock

@Composable
fun Level18Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (
            tShirt,
            spot1,
            spot2,
            spot3,
            spot4,
            spot5,
            counterBlock
        ) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.l18_t_shirt),
            modifier = Modifier.constrainAs(tShirt) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
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

        CounterBlock(
            modifier = Modifier.constrainAs(counterBlock) {
                top.linkTo(tShirt.bottom, margin = Dimensions.Padding.Medium.value)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            if (it == 5) {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.WRONG_CHOICE)
            }
        }
    }
}

@Preview
@Composable
fun Level18ContentPreview() {
    WordefullTheme {
        Level18Content(onLevelAction = {})
    }
}
