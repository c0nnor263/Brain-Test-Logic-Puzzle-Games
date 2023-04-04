package com.conboi.feature.level.all.level_2

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.feature.level.common.SunDraggable
import com.conboi.feature.level.common.Title

@Composable
internal fun Level2Content(onLevelAction: (LevelUIState) -> Unit) {

    @DrawableRes var owlImageRes by remember { mutableStateOf(R.drawable.owl_sleep) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Small.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            title = "Wake up the owl"
        )
        Spacer(modifier = Modifier.height(32.dp))

        BoxWithConstraints(contentAlignment = Alignment.Center) {

            SunDraggable(
                modifier = Modifier.align(Alignment.TopEnd),
            ) { sunOffset ->
                if (sunOffset.y >= maxHeight.value) {
                    owlImageRes = R.drawable.owl_wake
                    onLevelAction(LevelUIState.COMPLETED)
                }
            }
            ConstraintLayout(modifier = Modifier) {
                val (treeImage, owlImage) = createRefs()


                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.Padding.Small.value)
                        .constrainAs(treeImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    painter = painterResource(id = R.drawable.tree),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Crossfade(targetState = owlImageRes,
                    label = "",
                    modifier = Modifier
                        .size(128.dp)
                        .constrainAs(owlImage) {
                            top.linkTo(treeImage.top)
                            start.linkTo(treeImage.start, margin = 96.dp)
                            bottom.linkTo(treeImage.bottom)
                        }
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun Level2ContentPreview() {
    Level2Content(onLevelAction = {})
}