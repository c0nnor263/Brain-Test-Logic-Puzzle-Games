package com.gamovation.feature.level.level16

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.clickableNoRipple
import com.gamovation.core.ui.level.interactions.CollisionImage
import com.gamovation.core.ui.level.interactions.DraggableImage
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun Level16Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    var positionOfBowl by remember { mutableStateOf(Offset.Zero) }
    var isBowlShown by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (cat, bowl, food, plate) = createRefs()

        createHorizontalChain(
            plate,
            cat,
            food
        )

        Image(
            painter = painterResource(id = R.drawable.l16_cat),
            modifier = Modifier.constrainAs(cat) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("1:1")
                centerVerticallyTo(parent)
            },
            contentDescription = null
        )

        Image(
            painter = painterResource(id = R.drawable.l16_food),
            modifier = Modifier
                .constrainAs(food) {
                    width = Dimension.fillToConstraints
                    centerVerticallyTo(parent)
                }
                .clickableNoRipple {
                    isBowlShown = true
                },
            contentDescription = null
        )
        CollisionImage(
            defaultDrawableRes = R.drawable.l16_plate,
            modifier = Modifier.constrainAs(plate) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("4:3")
                centerAround(cat.bottom)
            },
            outerOffset = positionOfBowl
        ) {
            onLevelAction(LevelScreenState.USER_CORRECT_CHOICE)
        }

        Crossfade(
            modifier = Modifier.constrainAs(bowl) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("4:3")
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            targetState = isBowlShown,
            label = ""
        ) {
            if (it) {
                DraggableImage(
                    drawableRes = R.drawable.l16_bowl
                ) { bowlOffset, _ ->
                    positionOfBowl = bowlOffset
                }
            }
        }
    }
}

@Preview
@Composable
fun Level16ContentPreview() {
    WordefullTheme {
        Level16Content(onLevelAction = {})
    }
}
