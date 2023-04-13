package com.conboi.feature.level.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Durations
import com.conboi.core.ui.level.MAX_LEVEL_ID
import com.conboi.core.ui.level.NavigationArrows

@Composable
fun LevelNavigationBar(
    modifier: Modifier = Modifier,
    currentLevelId: Int,
    onLevelUpdate: (Int) -> Unit
) {
    val levelUIState = LocalLevelUIState.current

    AnimatedVisibility(
        modifier = modifier,
        visible = levelUIState == LevelUIState.PROCESSING,
        enter = fadeIn(tween(Durations.Short.time)) + slideInVertically(tween(Durations.Short.time)) { -it },
        exit = slideOutVertically(tween(Durations.Short.time)) { -it } + fadeOut(tween(Durations.Short.time))
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            NavigationArrows(
                currentIndex = currentLevelId,
                maxIndex = MAX_LEVEL_ID,
                onIndexUpdate = onLevelUpdate
            ) {
                Text(
                    text = "Level $currentLevelId",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }

}