package com.gamovation.feature.level.actionbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.theme.boardBackgroundColor

@Composable
fun Restarting(modifier: Modifier = Modifier, visible: Boolean) {
    AnimatedVisibility(
        visible,
        modifier = modifier,
        enter = fadeIn(tween(Durations.Short.time)),
        exit = fadeOut(tween(Durations.Short.time))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(boardBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible,
                enter = scaleIn(tween(Durations.Short.time)),
                exit = scaleOut(tween(Durations.Short.time))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(128.dp),
                    strokeWidth = 8.dp
                )
            }
        }
    }
}
