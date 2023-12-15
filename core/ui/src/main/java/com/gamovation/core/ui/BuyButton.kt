package com.gamovation.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.common.ScalableButton

@Composable
fun BuyButton(
    modifier: Modifier = Modifier,
    isLoaded: Boolean = true,
    text: String?,
    isDrawingAnimationEnabled: Boolean = false,
    onClick: () -> Unit
) {
    ScalableButton(
        modifier = modifier.defaultButtonIdleAnimation(),
        enabled = isLoaded,
        onClick = onClick,
        isDrawingAnimationEnabled = isDrawingAnimationEnabled
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.orange_button),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = if (isLoaded) {
                    null
                } else {
                    ColorFilter.tint(Color.Gray.copy(0.7F))
                }
            )

            if (isLoaded) {
                Text(
                    text = text ?: "...",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(16.dp),
                    strokeWidth = 2.dp
                )
            }
        }
    }
}
