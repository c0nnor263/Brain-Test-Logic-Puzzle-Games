package com.conboi.feature.level.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.feature.level.all.level_1.Level1Content
import com.conboi.feature.level.all.level_2.Level2Content
import com.conboi.feature.level.all.level_3.Level3Content

@Composable
fun LevelContents(
    modifier: Modifier = Modifier,
    currentLevelId: Int,
    isRestarting: Boolean,
    onLevelAction: (LevelUIState) -> Unit
) {
    Box(modifier = modifier) {
        // TODO Locked state LevelData

        if (isRestarting) {
            // TODO Restart level composable
            Level1Content(onLevelAction = onLevelAction)
        } else {
            when (currentLevelId) {
                1 -> Level1Content(onLevelAction = onLevelAction)
                2 -> Level2Content(onLevelAction = onLevelAction)
                3 -> Level3Content(onLevelAction = onLevelAction)
                else -> {}
            }
        }
    }
}