package com.gamovation.feature.level.level9

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.NumbersBlock

@Composable
fun Level9Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumbersBlock(numberMaxLength = 2) {
            if (it == "11") {
                onLevelAction(LevelScreenState.USER_CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.USER_WRONG_CHOICE)
            }
        }
    }
}
