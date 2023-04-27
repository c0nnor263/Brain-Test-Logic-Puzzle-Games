package com.conboi.feature.level.all.level_13

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.feature.level.common.answers.NumbersBlock

@Composable
fun Level13Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NumbersBlock {
            if (it == "5") {
                onLevelAction(LevelScreenState.CORRECT_CHOICE)
            }
        }

    }
}