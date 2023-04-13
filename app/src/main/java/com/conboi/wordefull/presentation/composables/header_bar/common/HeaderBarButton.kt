package com.conboi.wordefull.presentation.composables.header_bar.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Durations

@Composable
fun HeaderBarButton(modifier: Modifier = Modifier, @DrawableRes iconRes: Int, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isButtonPressed = interactionSource.collectIsPressedAsState()

    val scaleAnimation by animateFloatAsState(
        targetValue = if (isButtonPressed.value) 0.9f else 1f,
        animationSpec = tween(Durations.Short.time),
        label = ""
    )
    IconButton(
        modifier = modifier.scale(scaleAnimation),
        interactionSource = interactionSource,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}