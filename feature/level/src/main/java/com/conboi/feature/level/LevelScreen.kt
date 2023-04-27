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
import com.conboi.core.ui.state.LocalLevelActionState
import com.conboi.core.ui.state.LocalLevelScreenState
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.action_bar.LevelActionsBar
import com.conboi.feature.level.advice.GetAdviceDialog
import com.conboi.feature.level.common.LevelContents
import com.conboi.feature.level.common.answers.ResultLevelAlert
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(
    levelData: LevelData?,
    onForward: () -> Unit,
    onBack: () -> Unit,
    onUpdateLevelActionState: (LevelActionState) -> Unit,
    onUpdateLevelScreenState: (LevelScreenState) -> Unit,
    onAdviceResult: (ActionResult) -> Unit,
) {
    val levelScreenState = LocalLevelScreenState.current
    val levelActionState = LocalLevelActionState.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(levelScreenState) {
        scope.launch {
            if (levelScreenState == LevelScreenState.COMPLETED) {
                onForward()
            }
        }
    }






    Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                LevelContents(
                    modifier = Modifier.weight(1F),
                    level = levelData ?: LevelData(),
                    isRestarting = levelActionState == LevelActionState.RESTART,
                    onLevelAction = onUpdateLevelActionState,
                    onLevelUIAction = onUpdateLevelScreenState
                )

                Box(
                    modifier = Modifier.weight(0.15F),
                    contentAlignment = Alignment.Center
                ) {
                    LevelActionsBar(
                        modifier = Modifier.fillMaxSize(),
                        onRestart = { onUpdateLevelActionState(LevelActionState.RESTART) },
                        onGetAdvice = { onUpdateLevelActionState(LevelActionState.ADVICE) },
                        onSkip = { onUpdateLevelActionState(LevelActionState.SKIP) }
                    )
                }
            }


        GetAdviceDialog(
            levelActionState = levelActionState,
            levelData = levelData ?: LevelData(),
            onActionResult = onAdviceResult
        )

        ResultLevelAlert(
            currentState = levelScreenState,
            checkState = LevelScreenState.CORRECT_CHOICE
        )
        ResultLevelAlert(
            currentState = levelScreenState,
            checkState = LevelScreenState.WRONG_CHOICE
        )
        }


    BackHandler {
        if (levelScreenState == LevelScreenState.IS_PLAYING) {
            onBack()
        }
    }
}


@Preview
@Composable
fun LevelScreenPreview() {
    WordefullTheme {
        LevelScreen(
            levelData = LevelData(),
            onForward = {},
            onBack = {},
            onUpdateLevelActionState = {},
            onUpdateLevelScreenState = {},
            onAdviceResult = {}
        )
    }
}