package com.gamovation.feature.level.all.level20

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.feature.level.R

@Composable
fun Level20Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (dog, cat, pengiun) = createRefs()

        createHorizontalChain(
            dog,
            cat,
            pengiun
        )
        val bottomGuideline = createGuidelineFromTop(
            0.2F
        )

        Image(
            painter = painterResource(id = R.drawable.l20_dog),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(
                    dog
                ) {
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1")
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomGuideline)
                }
                .clickableNoRipple {
                    onLevelAction(LevelScreenState.WRONG_CHOICE)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.l20_cat),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(
                    cat
                ) {
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1")
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomGuideline)
                }
                .clickableNoRipple {
                    onLevelAction(LevelScreenState.FINAL)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.l20_pengiun),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(
                    pengiun
                ) {
                    width = Dimension.fillToConstraints
                    height = Dimension.ratio("1:1")
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomGuideline)
                }
                .clickableNoRipple {
                    onLevelAction(LevelScreenState.WRONG_CHOICE)
                }
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
