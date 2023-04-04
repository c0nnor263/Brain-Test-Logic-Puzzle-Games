package com.conboi.feature.level.common

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.R
import kotlin.math.roundToInt

@Composable
fun BoxWithConstraintsScope.SunDraggable(
    modifier: Modifier = Modifier,
    initialOffset: Offset = Offset.Zero,
    isEnabled: Boolean = true,
    onDrag: (Offset) -> Unit
) {

    var offsetX by remember { mutableStateOf(initialOffset.x) }
    var offsetY by remember { mutableStateOf(initialOffset.y) }
    Image(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .size(128.dp)
            .pointerInput(isEnabled) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    if (isEnabled) {
                        offsetX = (offsetX + dragAmount.x)
                            .coerceAtLeast(-maxWidth.value)
                            .coerceAtMost(maxWidth.value)
                        offsetY = (offsetY + dragAmount.y)
                            .coerceAtLeast(initialOffset.y)
                            .coerceAtMost(maxHeight.value)
                        onDrag(Offset(offsetX, offsetY))

                    }
                }
            },
        painter = painterResource(id = R.drawable.sun),
        contentDescription = null
    )
}