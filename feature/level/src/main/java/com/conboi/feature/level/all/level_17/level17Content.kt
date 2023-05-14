package com.conboi.feature.level.all.level_17

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.common.interactions.CollisionImage
import com.conboi.feature.level.common.interactions.DraggableImage

@Composable
fun Level17Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    var isBalloonPoppedOut by remember { mutableStateOf(false) }
    var ringsOffset by remember { mutableStateOf(Offset.Zero) }
    val balloonScaleAnimation by animateFloatAsState(
        targetValue = if (isBalloonPoppedOut) 0F else 1F,
        label = ""
    )


    Column(modifier = modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.boy),
                modifier = Modifier.weight(1F),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
            Text(
                text = if (isBalloonPoppedOut) "❤️" else "\uD83D\uDCA4",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
            Image(
                painter = painterResource(id = R.drawable.girl),
                modifier = Modifier.weight(1F),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

        BoxWithConstraints(Modifier.fillMaxWidth()) Scope@{
            Row(
                Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                this@Scope.apply {
                    DraggableImage(
                        isEnabled = isBalloonPoppedOut.not(),
                        drawableRes = R.drawable.rings,
                        maxSize = Offset(maxWidth.value, maxHeight.value)
                    ) {
                        ringsOffset = it
                    }
                }
                Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))

                AnimatedVisibility(
                    isBalloonPoppedOut.not(),
                    exit = scaleOut(tween(Durations.Medium.time))
                ) {
                    CollisionImage(
                        modifier = Modifier.scale(balloonScaleAnimation),
                        defaultDrawableRes = R.drawable.balloon,
                        outerOffset = ringsOffset
                    ) {
                        isBalloonPoppedOut = true
                        onLevelAction(LevelScreenState.CORRECT_CHOICE)
                    }

                }
                Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
                this@Scope.apply {
                    DraggableImage(
                        isEnabled = isBalloonPoppedOut.not(),
                        drawableRes = R.drawable.present,
                        maxSize = Offset(maxWidth.value, maxHeight.value)
                    ) {
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Level17ContentPreview() {
    WordefullTheme {
        Level17Content(onLevelAction = {})
    }
}