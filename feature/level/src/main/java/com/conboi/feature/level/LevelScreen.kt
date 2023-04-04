package com.conboi.feature.level

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.conboi.core.domain.ui.LevelActionState
import com.conboi.core.domain.ui.LevelUIState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.common.LevelActionsBar
import com.conboi.feature.level.common.LevelContents
import com.conboi.feature.level.common.LevelNavigationBar
import com.conboi.feature.level.common.ResultLevelAlert
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(
    modifier: Modifier = Modifier,
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
            if (levelUIState.value == LevelUIState.COMPLETED) {
                delay(DEFAULT_LEVEL_UI_COUNTDOWN_DURATION)
                currentLevelId = nextLevelId
            }
        }
    }

    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(
            modifier = Modifier.wrapContentSize(),
        ) {
            val (levelNavigationBar, levelContents, levelActionsBar) = createRefs()
            LevelNavigationBar(
                modifier = Modifier
                    .constrainAs(levelNavigationBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                currentLevelId = currentLevelId
            ) { newLevelId ->
                if (levelUIState.value == LevelUIState.PROCESSING) {
                    currentLevelId = newLevelId
                }
            }
            LevelContents(
                modifier = Modifier
                    .constrainAs(levelContents) {
                        top.linkTo(levelNavigationBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                currentLevelId = currentLevelId,
                isRestarting = levelActionState.value == LevelActionState.RESTART,
                onLevelAction = {
                    viewModel.updateLevelUIState(it)
                }
            )
            LevelActionsBar(
                modifier = Modifier
                    .constrainAs(levelActionsBar) {
                        top.linkTo(levelContents.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(Dimensions.Padding.Medium.value),
                onRestart = { viewModel.updateLevelActionState(LevelActionState.RESTART) },
                onGetAdvice = { viewModel.updateLevelActionState(LevelActionState.ADVICE) },
                onSkip = { viewModel.updateLevelActionState(LevelActionState.SKIP) }
            )


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



    BackHandler {
        currentLevelId = (currentLevelId - 1).coerceAtLeast(1)
    }
}


@Preview
@Composable
fun LevelScreenPreview() {
    LevelScreen(entryLevelId = 0, viewModel = hiltViewModel())
}