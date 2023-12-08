package com.gamovation.core.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.navigation.NavController
import com.gamovation.core.domain.ui.CLICK_DELAY
import com.gamovation.core.navigation.Screens
import com.gamovation.core.ui.animation.tweenLong
import com.gamovation.core.ui.animation.tweenTooLong

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
): Modifier {
    val mutableInteractionSource = remember { interactionSource ?: MutableInteractionSource() }
    return this.clickable(
        interactionSource = mutableInteractionSource,
        indication = null,
        enabled = enabled,
        onClick = onClick
    )
}

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.defaultButtonIdleAnimation(): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "Default Infinite Transition")
    val rotationAnimation by infiniteTransition.animateFloat(
        initialValue = -2F,
        targetValue = 2F,
        animationSpec = infiniteRepeatable(
            animation = tweenTooLong(),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Default Rotation Button"
    )
    val scaleAnimation by infiniteTransition.animateFloat(
        initialValue = 1F,
        targetValue = 1.05F,
        animationSpec = infiniteRepeatable(
            animation = tweenLong(),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Default Scale Button"
    )

    return this.rotate(rotationAnimation).scale(scaleAnimation)
}

var lastClickNavigateTime = 0L

fun NavController.navigate(screen: Screens) {
    val currentTime = System.currentTimeMillis()
    if (currentTime - lastClickNavigateTime < CLICK_DELAY) {
        return
    }
    lastClickNavigateTime = currentTime
    val route = screen.route
    if (currentDestination?.route != route) {
        navigate(route) {
            popUpTo(route) {
                inclusive = true
            }
        }
    }
}

var lastClickPopBackStackTime = 0L
fun NavController.popBack() {
    val currentTime = System.currentTimeMillis()
    if (currentTime - lastClickNavigateTime < CLICK_DELAY) {
        return
    }
    lastClickPopBackStackTime = currentTime
    popBackStack()
}
