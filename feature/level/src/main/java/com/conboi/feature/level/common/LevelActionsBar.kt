package com.conboi.feature.level.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R

@Composable
fun LevelActionsBar(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit,
    onGetAdvice: () -> Unit,
    onSkip: () -> Unit,
) {
    val levelUIState = LocalLevelUIState.current
    AnimatedVisibility(
        modifier = modifier,
        visible = levelUIState == LevelUIState.PROCESSING,
        enter = fadeIn(tween(Durations.Short.time)) + slideInVertically(tween(Durations.Short.time)) { it },
        exit = slideOutVertically(tween(Durations.Short.time)) { it } + fadeOut(tween(Durations.Short.time))
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = onRestart) {
                Icon(
                    painter = painterResource(id = R.drawable.restart_icon),
                    contentDescription = null
                )
            }

            IconButton(onClick = onGetAdvice) {
                Image(
                    painter = painterResource(id = R.drawable.lamp),
                    contentDescription = null
                )
            }

            IconButton(onClick = onSkip) {
                Icon(
                    painter = painterResource(id = R.drawable.skip_icon),
                    contentDescription = null
                )
            }
        }
    }
}