package com.gamovation.feature.level.all.level10

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.feature.level.common.answers.NumbersBlock

@Composable
fun Level10Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumbersBlock {
            if (it == "5") {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            } else {
                onLevelAction(LevelScreenState.WRONG_CHOICE)
            }
        }
    }
}
