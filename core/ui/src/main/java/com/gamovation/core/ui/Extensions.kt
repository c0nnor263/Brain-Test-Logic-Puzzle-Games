package com.gamovation.core.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gamovation.core.domain.ui.CLICK_DELAY
import com.gamovation.core.navigation.Screens

// TODO:clickableNoRipple

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
