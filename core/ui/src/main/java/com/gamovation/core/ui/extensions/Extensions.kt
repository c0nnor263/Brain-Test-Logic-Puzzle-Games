package com.gamovation.core.ui.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gamovation.core.navigation.Screens

fun Modifier.clickableNoRipple(enabled: Boolean = true, onClick: () -> Unit): Modifier {
    val interactionSource = MutableInteractionSource()
    return this.clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = enabled,
        onClick = onClick
    )
}


fun NavController.navigate(screen: Screens) {
    val route = screen.route
    if (currentDestination?.route != route) {
        navigate(route) {
            popUpTo(route) {
                inclusive = true
            }
        }
    }
}