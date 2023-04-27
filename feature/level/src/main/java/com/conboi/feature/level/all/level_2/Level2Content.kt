package com.conboi.feature.level.all.level_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.feature.level.common.Draggable

@Composable
internal fun Level2Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {

    val density = LocalDensity.current
    var isOwlWakeUp by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoxWithConstraints(contentAlignment = Alignment.Center) {


            Draggable(
                modifier = Modifier.then(Modifier.align(Alignment.TopEnd)),
                initialOffset = Offset(0f, -128f),
                isEnabled = !isOwlWakeUp,
                drawableRes = R.drawable.sun,
            ) { sunOffset ->
                if (sunOffset.y >= with(density) { maxHeight.roundToPx() }) {
                    isOwlWakeUp = true
                    onLevelAction(LevelScreenState.CORRECT_CHOICE)
                }
            }

            ConstraintLayout(modifier = Modifier.align(Alignment.Center)) {
                val (treeImage, owlImage) = createRefs()

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(treeImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)

                        },
                    painter = painterResource(id = R.drawable.tree),
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

@Preview
@Composable
fun Level2ContentPreview() {
    Level2Content(onLevelAction = {})
}