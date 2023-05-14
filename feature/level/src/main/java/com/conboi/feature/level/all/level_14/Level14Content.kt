package com.conboi.feature.level.all.level_14

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.feature.level.common.interactions.CollisionImage
import com.conboi.feature.level.common.interactions.DraggableText


@Composable
fun Level14Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    val levelScreenState = LocalLevelScreenState.current
    var positionOfText by remember { mutableStateOf(Offset.Zero) }


    BoxWithConstraints(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Где ", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.width(Dimensions.Padding.ExtraSmall.value))
                this@BoxWithConstraints.apply {
                    DraggableText(text = "черная",
                        style = MaterialTheme.typography.headlineSmall,
                        isEnabled = levelScreenState != LevelScreenState.CORRECT_CHOICE,
                        onDrag = {
                            positionOfText = it
                        })
                }
                Spacer(modifier = Modifier.width(Dimensions.Padding.ExtraSmall.value))
                Text(
                    text = "овечка?", style = MaterialTheme.typography.headlineSmall,
                )
            }
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.Padding.Medium.value)
            ) {
                val (sheep1, sheep2, sheep3) = createRefs()

                val verticalGuideLine = createGuidelineFromStart(0.5f)
                CollisionImage(
                    modifier = Modifier
                        .constrainAs(sheep1) {
                            width = Dimension.fillToConstraints
                            height = Dimension.ratio("4:3")
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(verticalGuideLine)
                            bottom.linkTo(sheep2.top)
                        },
                    matchedDrawableRes = R.drawable.black_sheep,
                    notMatchedDrawableRes = R.drawable.white_sheep,
                    outerOffset = positionOfText,

                    ) {
                    onLevelAction(LevelScreenState.CORRECT_CHOICE)
                }
                CollisionImage(
                    modifier = Modifier
                        .constrainAs(sheep2) {
                            width = Dimension.fillToConstraints
                            height = Dimension.ratio("4:3")
                            top.linkTo(parent.top)
                            start.linkTo(verticalGuideLine)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    matchedDrawableRes = R.drawable.black_sheep,
                    notMatchedDrawableRes = R.drawable.white_sheep,
                    outerOffset = positionOfText,

                    ) {
                    onLevelAction(LevelScreenState.CORRECT_CHOICE)
                }
                CollisionImage(
                    modifier = Modifier
                        .constrainAs(sheep3) {
                            width = Dimension.fillToConstraints
                            height = Dimension.ratio("4:3")
                            top.linkTo(sheep2.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(verticalGuideLine)
                            bottom.linkTo(parent.bottom)
                        },
                    matchedDrawableRes = R.drawable.black_sheep,
                    notMatchedDrawableRes = R.drawable.white_sheep,
                    outerOffset = positionOfText,

                    ) {
                    onLevelAction(LevelScreenState.CORRECT_CHOICE)
                }
            }
        }
    }
}


@Preview
@Composable
fun Level14ContentPreview() {
    Level14Content(onLevelAction = {})
}