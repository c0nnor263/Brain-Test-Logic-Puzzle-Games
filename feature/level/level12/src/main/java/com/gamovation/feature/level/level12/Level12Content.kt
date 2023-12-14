package com.gamovation.feature.level.level12

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.level.answers.LettersBlock
import com.gamovation.core.ui.state.LocalLocale
import java.util.Locale

@Composable
fun Level12Content(
    modifier: Modifier = Modifier,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val rightAnswer = stringResource(id = com.gamovation.core.domain.R.string.l12_right_answer)
    val locale = LocalLocale.current
    val defaultLanguage = remember { Locale.getDefault().language }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LettersBlock(
            language = locale?.language ?: defaultLanguage,
        ) {
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
