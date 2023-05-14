package com.conboi.feature.level.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.state.LocalLevelActionState
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.Completed
import com.conboi.feature.level.action_bar.Restarting
import kotlinx.coroutines.delay

@Composable
fun Contents(
    modifier: Modifier = Modifier,
    level: LevelData,
    onLevelAction: (LevelActionState) -> Unit,
    onLevelUIAction: (LevelScreenState) -> Unit
) {
    val screenState = LocalLevelScreenState.current
    val actionState = LocalLevelActionState.current

    LaunchedEffect(actionState) {
        if (actionState == LevelActionState.RESTART) {
            delay(300)
            onLevelAction(LevelActionState.IDLE)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {

        if (actionState == LevelActionState.RESTART) {
            Restarting(modifier = Modifier.fillMaxSize(), true)
            return@Box
        }



        when (screenState) {
            LevelScreenState.COMPLETED -> {
                Completed(
                    id = level.id - 1, onLevelUIAction = onLevelUIAction
                )
            }

            else -> LevelBody(level = level, onLevelUIAction = onLevelUIAction)
        }


    }
}


@Preview
@Composable
fun LevelContentsPreview() {
    WordefullTheme {
        Contents(level = LevelData(id = 2), onLevelAction = {}, onLevelUIAction = {})
    }
}