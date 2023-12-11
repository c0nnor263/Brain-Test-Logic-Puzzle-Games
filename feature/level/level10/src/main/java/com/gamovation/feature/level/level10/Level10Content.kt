package com.gamovation.feature.level.level10

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.NumbersBlock

@Composable
fun Level10Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumbersBlock {
            if (it == "5") {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_10_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_10_wrong
                    )
                )
            }
        }
    }
}
