package com.conboi.feature.level.common.interactions

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import com.conboi.core.ui.animation.DrawAnimation
import kotlin.math.roundToInt

@Composable
fun BoxWithConstraintsScope.DraggableText(
    modifier: Modifier = Modifier,
    initialOffset: Offset = Offset.Zero,
    style: TextStyle,
    isEnabled: Boolean = true,
    text: String,
    onDrag: (Offset) -> Unit
) {
    val screenWidthDp = with(LocalDensity.current) { maxWidth.roundToPx() }
    val screenHeightDp = with(LocalDensity.current) { maxHeight.roundToPx() }

    var offsetX by remember { mutableStateOf(initialOffset.x) }
    var offsetY by remember { mutableStateOf(initialOffset.y) }
    var currentPosition by remember { mutableStateOf(Offset.Zero) }

    DrawAnimation(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier
                .onGloballyPositioned {
                    currentPosition = it
                        .localToWindow(Offset.Zero)
                        .run { Offset(x, y) }
                }
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
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
            style = style
        )
    }
}