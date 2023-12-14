package com.gamovation.feature.level.level13

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.NumbersBlock

@Composable
fun Level13Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumbersBlock {
            if (it == "6") {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_13_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_13_wrong
                    )
                )
            }
        }
    }
}
