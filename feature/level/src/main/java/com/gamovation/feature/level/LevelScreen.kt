package com.gamovation.feature.level

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.domain.level.LevelActionState
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.level.answers.LevelUserChoiceAlert
import com.gamovation.core.ui.state.LocalLevelAction
import com.gamovation.core.ui.state.LocalLevelScreen
import com.gamovation.core.ui.state.LocalReviewDataHandlerState
import com.gamovation.feature.level.actionbar.ActionBar
import com.gamovation.feature.level.actionbar.dialog.ActionBarDialog
import com.gamovation.feature.level.common.LevelSpecificContent
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(
    viewModel: LevelScreenViewModel,
    idArg: Int,
    onNavigateToStore: () -> Unit
) {
    val reviewDataHandler = LocalReviewDataHandlerState.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val levelData by viewModel.levelData.collectAsStateWithLifecycle()
    val screenState by viewModel.levelScreenState.collectAsStateWithLifecycle()
    val actionState by viewModel.levelActionState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateLevelIndex(idArg)
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            LevelScreenViewModel.UiState.OnBack -> {
                viewModel.onBackLevel()
                viewModel.clearUiState()
            }

            LevelScreenViewModel.UiState.OnForward -> {
                viewModel.onForwardLevel()
                viewModel.clearUiState()
            }

            LevelScreenViewModel.UiState.OnWatchAd -> {
                viewModel.watchAdReward()
                viewModel.clearUiState()
            }

            LevelScreenViewModel.UiState.OnReviewRequest -> {
                val activity = context as ComponentActivity
                viewModel.processReviewRequest(activity) {
                    reviewDataHandler.isGranted = true
                }
                viewModel.clearUiState()
            }

            is LevelScreenViewModel.UiState.OnUpdateLevelActionState -> {
                viewModel.updateLevelActionState(state.actionState)
                viewModel.clearUiState()
            }

            is LevelScreenViewModel.UiState.OnUpdateLevelScreenState -> {
                viewModel.updateLevelScreenState(state.screenState)
                viewModel.clearUiState()
            }

            null -> {}
        }
    }

    LaunchedEffect(screenState) {
        scope.launch {
            val state = when (screenState) {
                LevelScreenState.LEVEL_COMPLETED -> LevelScreenViewModel.UiState.OnForward
                LevelScreenState.USER_WATCH_AD -> LevelScreenViewModel.UiState.OnWatchAd
                else -> null
            }
            state?.let { viewModel.updateUiState(it) }
        }
    }

    CompositionLocalProvider(
        LocalLevelScreen provides screenState,
        LocalLevelAction provides actionState
    ) {
        LevelScreenContent(
            level = levelData,
            onActionResult = { result ->
                when (result.type) {
                    ActionResult.Type.SUCCESS -> {
                        viewModel.buyAction(cost = result.cost)
                    }

                    ActionResult.Type.BUY_MORE -> {
                        onNavigateToStore()
                    }

                    else -> {}
                }
                viewModel.updateLevelActionState(LevelActionState.IDLE)
            },
            onReviewRequest = {
                viewModel.updateUiState(LevelScreenViewModel.UiState.OnReviewRequest)
            },
            onUpdateLevelActionState = { actionState ->
                val state = LevelScreenViewModel.UiState.OnUpdateLevelActionState(actionState)
                viewModel.updateUiState(state)
            },
            onUpdateLevelScreenState = { screenState ->
                val state = LevelScreenViewModel.UiState.OnUpdateLevelScreenState(screenState)
                viewModel.updateUiState(state)
            }
        )
    }

    BackHandler {
        if (screenState == LevelScreenState.IS_LEVEL_PLAYING) {
            viewModel.updateUiState(LevelScreenViewModel.UiState.OnBack)
        }
    }
}

@Composable
fun LevelScreenContent(
    level: LevelData?,
    onActionResult: (ActionResult) -> Unit,
    onReviewRequest: () -> Unit,
    onUpdateLevelActionState: (LevelActionState) -> Unit,
    onUpdateLevelScreenState: (LevelScreenState) -> Unit
) {
    val screenState = LocalLevelScreen.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (contents, actionBar) = createRefs()

            val bottomGuideLine = createGuidelineFromBottom(0.1f)

            level?.let {
                LevelSpecificContent(
                    modifier = Modifier.constrainAs(contents) {
                        width = Dimension.matchParent
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(bottomGuideLine)
                    },
                    level = it,
                    onLevelAction = { action ->
                        onUpdateLevelActionState(action)
                    },
                    onLevelScreenAction = onUpdateLevelScreenState,
                    onReviewRequest = onReviewRequest
                )
            }

            ActionBar(
                modifier = Modifier
                    .constrainAs(actionBar) {
                        width = Dimension.matchParent
                        top.linkTo(bottomGuideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = Dimensions.Padding.Medium.value)
                    },
                onUpdateLevelActionState = onUpdateLevelActionState
            )
        }

        level?.let {
            ActionBarDialog(
                levelData = it,
                onActionResult = onActionResult
            )
        }

        LevelUserChoiceAlert(
            currentState = screenState,
            checkState = LevelScreenState.USER_CORRECT_CHOICE
        )
        LevelUserChoiceAlert(
            currentState = screenState,
            checkState = LevelScreenState.USER_WRONG_CHOICE
        )
    }
}
