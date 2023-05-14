package com.conboi.feature.level.common.interactions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource

@Composable
fun CollisionImage(
    modifier: Modifier = Modifier,
    @DrawableRes defaultDrawableRes: Int? = null,
    @DrawableRes matchedDrawableRes: Int? = null,
    @DrawableRes notMatchedDrawableRes: Int? = null,
    outerOffset: Offset,
    onMatch: () -> Unit
) {
    var rectOfImage by remember { mutableStateOf<Rect?>(null) }
    var isMatched by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = outerOffset) {
        if (rectOfImage?.contains(outerOffset) == true) {
            isMatched = true
            onMatch()
        }
    }

    val drawableRes = defaultDrawableRes ?: run {
        if (isMatched) {
            matchedDrawableRes
        } else {
            notMatchedDrawableRes
        }
    } ?: return

    Image(
        painter = painterResource(id = drawableRes),
        modifier = modifier.onGloballyPositioned { coordinates ->
            rectOfImage = coordinates.boundsInWindow()
        },
        contentDescription = null,
    )

}