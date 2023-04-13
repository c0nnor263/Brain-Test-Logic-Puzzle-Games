package com.conboi.feature.level

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.conboi.core.domain.ui.LevelActionState
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.feature.level.common.LevelActionsBar
import com.conboi.feature.level.common.LevelContents
import com.conboi.feature.level.common.LocalLevelUIState
import com.conboi.feature.level.common.ResultLevelAlert
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(
    viewModel: LevelScreenViewModel = hiltViewModel(),
    entryLevelId: Int,
) {
    var currentLevelId by rememberSaveable { mutableStateOf(entryLevelId) }
    val levelUIState = viewModel.levelUIState.collectAsStateWithLifecycle()
    val levelActionState = viewModel.levelActionState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    LaunchedEffect(levelUIState.value) {
        scope.launch {
            val nextLevelId = currentLevelId + 1
            if (levelUIState.value == LevelUIState.WAITING) {
                currentLevelId = nextLevelId
            }
        }
    }



    CompositionLocalProvider(LocalLevelUIState provides levelUIState.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                LevelContents(
                    modifier = Modifier.weight(1F),
                    currentLevelId = currentLevelId,
                    isRestarting = levelActionState.value == LevelActionState.RESTART,
                    onLevelAction = {
                        viewModel.updateLevelActionState(it)
                    },
                    onLevelUIAction = {
                        viewModel.updateLevelUIState(it)
                    },
                )

                Box(
                    modifier = Modifier.weight(0.15F),
                    contentAlignment = Alignment.Center
                ) {
                    LevelActionsBar(
                        modifier = Modifier.fillMaxSize(),
                        onRestart = { viewModel.updateLevelActionState(LevelActionState.RESTART) },
                        onGetAdvice = { viewModel.updateLevelActionState(LevelActionState.ADVICE) },
                        onSkip = { viewModel.updateLevelActionState(LevelActionState.SKIP) }
                    )
                }


            }



            ResultLevelAlert(
                currentState = levelUIState.value,
                checkState = LevelUIState.COMPLETED
            )
            ResultLevelAlert(
                currentState = levelUIState.value,
                checkState = LevelUIState.FAILED
            )
        }

    }


    BackHandler {
        if (levelUIState.value == LevelUIState.PROCESSING) {
            currentLevelId = (currentLevelId - 1).coerceAtLeast(1)
        }
    }
}


@Preview
@Composable
fun LevelScreenPreview() {
    LevelScreen(entryLevelId = 0, viewModel = hiltViewModel())
}