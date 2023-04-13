package com.conboi.feature.level.all.level_3

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.animation.DrawAnimation

@Composable
fun Level3IceCream(
    modifier: Modifier = Modifier,
    index: Int,
    transition: Transition<Boolean>,
    isNotIceCream: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iceCreamScaleAnimation by transition.animateFloat(
        transitionSpec = {
            tween(Durations.Long.time)
        },
        label = "",
    ) { state ->
        if (state) 0.75F else 1F
    }
    val flyScaleAnimation by transition.animateFloat(
        transitionSpec = {
            tween(Durations.Long.time)
        },
        label = ""
    ) { state ->
        if (state) 1F else 0F
    }
    DrawAnimation(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = transition.currentState,
                onClick = onClick
            )
            .scale(scaleX = 1F, scaleY = iceCreamScaleAnimation),
        delay = Durations.Medium.time.toLong() * index,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ice_cream),
            contentDescription = null
        )
        if (isNotIceCream) {
            Image(
                modifier = Modifier.scale(flyScaleAnimation),
                painter = painterResource(id = R.drawable.fly),
                contentDescription = null
            )
        }
    }

}
