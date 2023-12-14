package com.gamovation.feature.level.level5

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
internal fun Level5TicTacToeCell(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int? = null,
    interactionSource: MutableInteractionSource? = null,
    alpha: Float = 1F,
    appearOrder: Int? = null
) {
    drawableRes?.let {
        if (appearOrder != null) {
            DrawAnimation(
                modifier = modifier.padding(Dimensions.Padding.Small.value),
                appearOrder = appearOrder
            ) {
                Level5TicTacToeImage(
                    drawableRes = drawableRes,
                    interactionSource = interactionSource,
                    alpha = alpha
                )
            }
        } else {
            Level5TicTacToeImage(
                modifier = modifier.padding(Dimensions.Padding.Small.value),
                drawableRes = drawableRes,
                interactionSource = interactionSource,
                alpha = alpha
            )
        }
    }
}

@Composable
internal fun Level5TicTacToeImage(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    interactionSource: MutableInteractionSource?,
    alpha: Float
) {
    val localModifier = remember {
        interactionSource?.let {
            modifier.clickable(
                interactionSource = it,
                indication = null,
                onClick = {
                }
            )
        } ?: modifier
    }
    Image(
        modifier = localModifier,
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        alpha = alpha
    )
}
