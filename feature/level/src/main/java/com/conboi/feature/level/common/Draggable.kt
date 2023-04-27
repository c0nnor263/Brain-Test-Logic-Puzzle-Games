package com.conboi.feature.level.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.animation.DrawAnimation
import kotlin.math.roundToInt

@Composable
fun BoxWithConstraintsScope.Draggable(
    modifier: Modifier = Modifier,
    initialOffset: Offset = Offset.Zero,
    isEnabled: Boolean = true,
    @DrawableRes drawableRes: Int,
    onDrag: (Offset) -> Unit
) {
    val screenWidthDp = with(LocalDensity.current) { maxWidth.roundToPx() }
    val screenHeightDp = with(LocalDensity.current) { maxHeight.roundToPx() }

    var offsetX by remember { mutableStateOf(initialOffset.x) }
    var offsetY by remember { mutableStateOf(initialOffset.y) }
    var currentPosition by remember { mutableStateOf(Offset.Zero) }

    DrawAnimation(modifier = modifier) {
        Image(
            modifier = Modifier
                .onGloballyPositioned {
                    currentPosition = it
                        .localToWindow(Offset.Zero)
                        .run {
                            Offset(
                                x.coerceAtLeast(97F),
                                y.coerceAtLeast(97F)
                            )
                        }
                }
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(96.dp)
                .pointerInput(isEnabled) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        if (isEnabled) {
                            offsetX = (offsetX + dragAmount.x).coerceIn(
                                -currentPosition.x,
                                (screenWidthDp.toFloat() - currentPosition.x)
                            )
                            offsetY = (offsetY + dragAmount.y).coerceIn(
                                initialOffset.y,
                                (screenHeightDp.toFloat())
                            )
                            onDrag(Offset(offsetX, offsetY) + currentPosition)
                        }


                    }
                },
            painter = painterResource(id = drawableRes),
            contentDescription = null
        )
    }
}