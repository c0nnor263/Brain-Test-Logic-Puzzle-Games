package com.gamovation.feature.level.level12

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.LettersBlock

@Composable
fun Level12Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val rightAnswer = stringResource(id = com.gamovation.core.domain.R.string.l12_right_answer)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LettersBlock {
            if (it == rightAnswer) {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_12_finished
                    )
                )
            } else {
                onLevelAction(
                    LevelScreenState.UserWrongChoice(
                        com.gamovation.core.domain.R.string.event_level_12_wrong
                    )
                )
            }
        }
    }
}
