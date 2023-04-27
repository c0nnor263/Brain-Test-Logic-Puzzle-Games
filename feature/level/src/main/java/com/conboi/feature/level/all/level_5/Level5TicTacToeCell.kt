package com.conboi.feature.level.all.level_5

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.animation.DrawAnimation

@Composable
fun Level5TicTacToeCell(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    interactionSource: MutableInteractionSource? = null,
    alpha: Float = 1F,
    delay: Long? = null
) {
    val modifierWithInteractionSource = if (interactionSource != null) {
        modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {}
    } else modifier


    val imageContent: @Composable (Modifier) -> Unit = {
        Image(
            modifier = it,
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            alpha = alpha
        )
    }


    delay?.let { delay ->
        DrawAnimation(modifier = modifierWithInteractionSource, delay = delay) {
            imageContent(Modifier)
        }
    } ?: imageContent(modifierWithInteractionSource)

}