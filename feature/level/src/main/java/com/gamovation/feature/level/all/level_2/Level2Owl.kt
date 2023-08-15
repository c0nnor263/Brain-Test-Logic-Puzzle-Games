package com.gamovation.feature.level.all.level_2

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
fun Level2Owl(
    modifier: Modifier = Modifier,
    isWakeUp: Boolean
) {
    @DrawableRes
    val owlImageRes by remember(isWakeUp) {
        mutableStateOf(if (isWakeUp) R.drawable.l2_owl_wake else R.drawable.l2_owl_sleep)
    }

    DrawAnimation(
        modifier = modifier,
        delayOrder = 1
    ) {
        Crossfade(
            targetState = owlImageRes,
            label = "",
        ) {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}
