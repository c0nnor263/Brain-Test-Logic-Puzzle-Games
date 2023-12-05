package com.gamovation.feature.level.level6

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.NumbersBlock

@Composable
fun Level6Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumbersBlock(numberMaxLength = 2) {
            if (it == "10") {
                onLevelAction(LevelScreenState.USER_CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.USER_WRONG_CHOICE)
            }
        }
    }
}
