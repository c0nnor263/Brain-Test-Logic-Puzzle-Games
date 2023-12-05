package com.gamovation.feature.level.level2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.level.interactions.DraggableImage

@Composable
fun Level2Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    var isOwlWakeUp by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            ConstraintLayout(modifier = Modifier.align(Alignment.Center)) {
                val (treeImage, sun, owlImage) = createRefs()
                DraggableImage(
                    modifier = Modifier.constrainAs(sun) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                    isEnabled = !isOwlWakeUp,
                    drawableRes = com.gamovation.core.ui.R.drawable.sun
                ) { sunOffset, screenSize ->
                    if (sunOffset.y >= screenSize.y / 1.5F) {
                        isOwlWakeUp = true
                        onLevelAction(LevelScreenState.USER_CORRECT_CHOICE)
                    }
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(treeImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    painter = painterResource(id = R.drawable.l2_tree),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                Level2Owl(
                    modifier = Modifier
                        .size(82.dp)
                        .constrainAs(owlImage) {
                            top.linkTo(treeImage.top, margin = 64.dp)
                            start.linkTo(treeImage.start, margin = 128.dp)
                            bottom.linkTo(treeImage.bottom)
                        },
                    isWakeUp = isOwlWakeUp
                )
            }
        }
    }
}
