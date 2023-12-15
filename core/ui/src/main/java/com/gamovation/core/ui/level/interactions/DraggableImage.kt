package com.gamovation.core.ui.level.interactions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DraggableImage(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    orientation: DraggableOrientation = DraggableOrientation.Free,
    @DrawableRes drawableRes: Int,
    onDrag: (Offset, Offset) -> Unit = { _, _ -> }
) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidth = with(density) {
        configuration.screenWidthDp.dp.roundToPx()
    }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.roundToPx() }

    var offsetX by remember { mutableFloatStateOf(0F) }
    var offsetY by remember { mutableFloatStateOf(0F) }
    var originalPosition by remember { mutableStateOf(Offset.Zero) }
    var rectOfDraggable by remember { mutableStateOf<Rect?>(null) }

    val screenWidthDraggable = remember(rectOfDraggable) {
        screenWidth - (rectOfDraggable?.width ?: 0F)
    }
    val screenHeightDraggable = remember(rectOfDraggable) {
        screenHeight - (rectOfDraggable?.height ?: 0F)
    }
    val screenSizeDraggable = remember(screenWidthDraggable, screenHeightDraggable) {
        Offset(
            screenWidthDraggable,
            screenHeightDraggable
        )
    }


    Image(
        modifier = modifier
            .requiredSize(64.dp, 64.dp)
            .onGloballyPositioned { coordinates ->
                rectOfDraggable = coordinates.boundsInWindow()
                originalPosition = coordinates
                    .localToWindow(Offset.Zero)
                    .run { Offset(x, y) }
            }
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(isEnabled) {
                fun calculateOffset(dragAmount: Offset) {
                    if (isEnabled) {
                        offsetX = (offsetX + dragAmount.x)
                            .coerceIn(
                                -originalPosition.x,
                                screenWidthDraggable - originalPosition.x
                            )
                        offsetY = (offsetY + dragAmount.y)
                            .coerceIn(
                                -originalPosition.y,
                                screenHeightDraggable - originalPosition.y
                            )

                        val draggableOffset = Offset(offsetX, offsetY) + originalPosition

                        onDrag(
                            draggableOffset,
                            screenSizeDraggable
                        )
                    }
                }

                when (orientation) {
                    DraggableOrientation.Horizontal -> {
                        detectHorizontalDragGestures { change, dragAmount ->
                            change.consume()
                            calculateOffset(Offset(dragAmount, 0F))
                        }
                    }

                    DraggableOrientation.Vertical -> {
                        detectVerticalDragGestures { change, dragAmount ->
                            change.consume()
                            calculateOffset(Offset(0F, dragAmount))
                        }
                    }

                    DraggableOrientation.Free -> {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            calculateOffset(dragAmount)
                        }
                    }
                }

            },
        painter = painterResource(id = drawableRes),
        contentDescription = null
    )
}
