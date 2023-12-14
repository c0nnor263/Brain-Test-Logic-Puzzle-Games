package com.gamovation.core.ui.common

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.tweenEasy
import com.gamovation.core.ui.clickableNoRipple

@Composable
fun ScalableButton(
    modifier: Modifier = Modifier,
    appearOrder: Int = 0,
    isDrawingAnimationEnabled: Boolean = true,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium,
    maxLines: Int = Int.MAX_VALUE,
    @StringRes stringRes: Int? = null,
    onClick: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    if (isDrawingAnimationEnabled) {
        DrawAnimation(modifier = modifier, appearOrder = appearOrder) {
            ScalableButtonContent(
                modifier = modifier,
                stringRes = stringRes,
                textStyle = textStyle,
                maxLines = maxLines,
                enabled = enabled,
                onClick = onClick,
                content = content
            )
        }
    } else {
        ScalableButtonContent(
            modifier = modifier,
            stringRes = stringRes,
            textStyle = textStyle,
            maxLines = maxLines,
            enabled = enabled,
            onClick = onClick,
            content = content
        )
    }
}

@Composable
internal fun ScalableButtonContent(
    modifier: Modifier = Modifier,
    @StringRes stringRes: Int?,
    textStyle: TextStyle,
    maxLines: Int,
    enabled: Boolean,
    onClick: () -> Unit,
    content: (@Composable () -> Unit)?
) {
    val interaction = remember { MutableInteractionSource() }
    val isPressed = interaction.collectIsPressedAsState()

    val scaleButtonAnimation by animateFloatAsState(
        targetValue =
        if (isPressed.value) {
            0.9f
        } else {
            1f
        },
        animationSpec = tweenEasy(),
        label = "Scale Button Animation"
    )
    Row(
        modifier = modifier
            .padding(Dimensions.Padding.Small.value)
            .scale(scaleButtonAnimation)
            .clickableNoRipple(
                onClick = {
                    onClick()
                },
                enabled = enabled,
                interactionSource = interaction
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        content?.let {
            it()
        } ?: stringRes?.let {
            Text(
                text = stringResource(it),
                style = textStyle,
                maxLines = maxLines,
                modifier = Modifier.padding(Dimensions.Padding.Medium.value)
            )
        }
    }
}
