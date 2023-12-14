package com.gamovation.feature.level.level2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.interactions.DraggableImage

@Composable
fun Level2Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    var isOwlWakeUp by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (treeImageRef, sunRef, owlImageRef) = createRefs()

        DraggableImage(
            modifier = Modifier.constrainAs(sunRef) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            isEnabled = !isOwlWakeUp,
            drawableRes = com.gamovation.core.ui.R.drawable.sun
        ) { sunOffset, screenSize ->
            if (sunOffset.y >= screenSize.y / 1.25) {
                isOwlWakeUp = true
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_2_finished
                    )
                )
            }
        }
        Image(
            modifier = Modifier
                .constrainAs(treeImageRef) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = R.drawable.l2_tree),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Level2Owl(
            modifier = Modifier
                .size(82.dp)
                .constrainAs(owlImageRef) {
                    top.linkTo(treeImageRef.top, margin = 64.dp)
                    start.linkTo(treeImageRef.start, margin = 128.dp)
                    bottom.linkTo(treeImageRef.bottom)
                },
            isWakeUp = isOwlWakeUp
        )
    }
}
