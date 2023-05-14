package com.conboi.feature.level.common.interactions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
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
import com.conboi.core.ui.animation.DrawAnimation
import kotlin.math.roundToInt

@Composable
fun DraggableImage(
    modifier: Modifier = Modifier,
    maxSize: Offset,
    isEnabled: Boolean = true,
    @DrawableRes drawableRes: Int,
    onDrag: (Offset) -> Unit
) {
    val screenWidthDp = with(LocalDensity.current) { maxSize.x.toDp().roundToPx() }
    val screenHeightDp = with(LocalDensity.current) { maxSize.y.toDp().roundToPx() }

    var offsetX by remember { mutableStateOf(0F) }
    var offsetY by remember { mutableStateOf(0F) }
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
                .pointerInput(isEnabled) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        if (isEnabled) {

                            // TO DO - fix this
                            offsetX = (offsetX + dragAmount.x)
//                                .coerceIn(
//                                -currentPosition.x,
//                                (screenWidthDp.toFloat() - currentPosition.x)
//                            )
                            offsetY = (offsetY + dragAmount.y)
//                                .coerceIn(
//                                0F,
//                                (screenHeightDp.toFloat())
//                            )
                            onDrag(Offset(offsetX, offsetY) + currentPosition)
                        }


                    }
                },
            painter = painterResource(id = drawableRes),
            contentDescription = null,
        )
    }
}