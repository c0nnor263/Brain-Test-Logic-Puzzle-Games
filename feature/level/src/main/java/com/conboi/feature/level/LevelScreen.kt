package com.conboi.feature.level

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.ActionResult
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.action_bar.ActionBar
import com.conboi.feature.level.action_bar.dialog.ActionBarDialog
import com.conboi.feature.level.common.Contents
import com.conboi.feature.level.common.answers.ResultLevelAlert
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(
    level: LevelData?,
    onForward: () -> Unit,
    onBack: () -> Unit,
    onUpdateLevelActionState: (LevelActionState) -> Unit,
    onUpdateLevelScreenState: (LevelScreenState) -> Unit,
    onActionResult: (ActionResult) -> Unit,
) {
    val screenState = LocalLevelScreenState.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(screenState) {
        scope.launch {
            if (screenState == LevelScreenState.COMPLETED) {
                onForward()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Contents(
                modifier = Modifier.weight(1F),
                level = level ?: LevelData(),
                onLevelAction = onUpdateLevelActionState,
                onLevelScreenAction = onUpdateLevelScreenState
            )

            ActionBar(
                modifier = Modifier
                    .weight(0.15F)
                    .fillMaxSize(),
                onRestart = { onUpdateLevelActionState(LevelActionState.RESTART) },
                onGetAdvice = { onUpdateLevelActionState(LevelActionState.ADVICE) },
                onSkip = { onUpdateLevelActionState(LevelActionState.SKIP) }
            )
        }


        ActionBarDialog(
            levelData = level ?: LevelData(),
            onActionResult = onActionResult
        )

        ResultLevelAlert(
            currentState = screenState,
            checkState = LevelScreenState.CORRECT_CHOICE
        )
        ResultLevelAlert(
            currentState = screenState,
            checkState = LevelScreenState.WRONG_CHOICE
        )
    }


    BackHandler {
        if (screenState == LevelScreenState.IS_PLAYING) {
            onBack()
        }
    }
}


@Preview
@Composable
fun LevelScreenPreview() {
    WordefullTheme {
        LevelScreen(
            level = LevelData(),
            onForward = {},
            onBack = {},
            onUpdateLevelActionState = {},
            onUpdateLevelScreenState = {},
            onActionResult = {}
        )
    }
}