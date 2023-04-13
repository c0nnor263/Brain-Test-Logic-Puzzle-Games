package com.conboi.core.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

fun <T> defaultSpringAnimation() = spring<T>(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessLow
)