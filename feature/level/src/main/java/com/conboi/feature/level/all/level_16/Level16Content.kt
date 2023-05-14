package com.conboi.feature.level.all.level_16

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.R
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.common.interactions.CollisionImage
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level16Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {

    var positionOfBowl by remember { mutableStateOf(Offset.Zero) }
    var isBowlShown by remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        val maxSize = Offset(maxWidth.value, maxHeight.value)
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (cat, bowl, food, plate) = createRefs()


            createHorizontalChain(
                plate, cat, food
            )

            Image(
                painter = painterResource(id = R.drawable.cat),
                modifier = Modifier.constrainAs(cat) {
                    width = Dimension.fillToConstraints
                    centerVerticallyTo(parent)
                },
                contentDescription = null,
            )

            Image(
                painter = painterResource(id = R.drawable.food),
                modifier = Modifier
                    .constrainAs(food) {
                        width = Dimension.fillToConstraints
                        centerVerticallyTo(parent)
                    }
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) {
                        isBowlShown = true
                    },
                contentDescription = null,
            )
            CollisionImage(
                defaultDrawableRes = R.drawable.plate,
                modifier = Modifier.constrainAs(plate) {
                    width = Dimension.fillToConstraints
                    centerVerticallyTo(parent)
                },
                outerOffset = positionOfBowl
            ) {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            }


            Crossfade(modifier = Modifier.constrainAs(bowl) {
                width = Dimension.fillToConstraints
                height = Dimension.ratio("4:3")
                top.linkTo(parent.top)
                end.linkTo(parent.end)

            }, targetState = isBowlShown, label = "") {
                if (it) {
                    DraggableImage(
                        modifier = Modifier.scale(2F),
                        maxSize = maxSize,
                        drawableRes = R.drawable.bowl
                    ) { offset ->
                        positionOfBowl = offset
                    }
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