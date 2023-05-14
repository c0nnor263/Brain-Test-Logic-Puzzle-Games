package com.conboi.feature.level.action_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.domain.currency.DEFAULT_ADVICE_COST
import com.conboi.core.domain.currency.DEFAULT_SKIP_COST
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.state.LocalLevelScreenState

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit,
    onGetAdvice: () -> Unit,
    onSkip: () -> Unit,
) {
    val levelUIState = LocalLevelScreenState.current
    AnimatedVisibility(
        modifier = modifier,
        visible = levelUIState == LevelScreenState.IS_PLAYING,
        enter = fadeIn(tween(Durations.Short.time)) + slideInVertically(tween(Durations.Short.time)) { it },
        exit = slideOutVertically(tween(Durations.Short.time)) { it } + fadeOut(tween(Durations.Short.time))
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onRestart) {
                Icon(
                    painter = painterResource(id = R.drawable.restart_icon),
                    contentDescription = null
                )
            }

            Box(contentAlignment = Alignment.Center) {
                IconButton(
                    modifier = Modifier.padding(Dimensions.Padding.Small.value),
                    onClick = onGetAdvice
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lamp),
                        contentDescription = null
                    )
                }
                Text(
                    "$DEFAULT_ADVICE_COST",
                    modifier = Modifier.align(Alignment.BottomEnd),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Box(contentAlignment = Alignment.Center) {
                IconButton(
                    modifier = Modifier.padding(Dimensions.Padding.Small.value),
                    onClick = onSkip
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.skip_icon),
                        contentDescription = null
                    )
                }
                Text(
                    "$DEFAULT_SKIP_COST",
                    modifier = Modifier.align(Alignment.BottomEnd),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}