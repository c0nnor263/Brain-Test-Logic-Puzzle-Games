package com.gamovation.tilecl.presentation.composables.header_bar.options

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.Durations
import com.gamovation.tilecl.presentation.composables.header_bar.common.HeaderBarButton

@Composable
fun RowScope.HeaderBarLevelOption(
    onNavigateToHome: () -> Unit,
    onNavigateToMenu: () -> Unit,
) {
    var expandRow by remember { mutableStateOf(false) }

    Crossfade(
        targetState = expandRow,
        label = "",
        animationSpec = tween(Durations.ShortMedium.time)
    ) {
        HeaderBarButton(iconRes = if (it) R.drawable.baseline_arrow_left_24 else R.drawable.baseline_arrow_right_24) {
            expandRow = !expandRow
        }
    }

    AnimatedVisibility(
        visible = expandRow,
        enter = fadeIn(tween(Durations.Short.time)) +
                slideInHorizontally(tween(Durations.Short.time)) { -it },
        exit = slideOutHorizontally(tween(Durations.Short.time)) { -it } +
                fadeOut(tween(Durations.Short.time)),
    ) {
        HeaderBarButton(iconRes = R.drawable.home_icon, onClick = onNavigateToHome)
    }

    AnimatedVisibility(
        visible = expandRow,
        enter = fadeIn(tween(Durations.Short.time)) +
                slideInHorizontally(tween(Durations.Short.time)) { -it },
        exit = slideOutHorizontally(tween(Durations.Short.time)) { -it * 2 } +
                fadeOut(tween(Durations.Short.time)),
    ) {
        HeaderBarButton(iconRes = R.drawable.baseline_more_horiz_24, onClick = onNavigateToMenu)
    }
}