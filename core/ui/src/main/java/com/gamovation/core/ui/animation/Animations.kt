package com.gamovation.core.ui.animation

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

fun <T> defaultSpringAnimation() = spring<T>(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessLow
)

fun <T> tweenMedium(delayMillis: Int = 0, easing: Easing = FastOutSlowInEasing): TweenSpec<T> {
    return tween(Durations.Medium.time, delayMillis = delayMillis, easing = easing)
}

fun <T> tweenEasy(delayMillis: Int = 0, easing: Easing = FastOutSlowInEasing): TweenSpec<T> {
    return tween(Durations.Short.time, delayMillis = delayMillis, easing = easing)
}

fun <T> tweenLong(delayMillis: Int = 0, easing: Easing = FastOutSlowInEasing): TweenSpec<T> {
    return tween(Durations.Long.time, delayMillis = delayMillis, easing = easing)
}
