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
import com.gamovation.feature.level.domain.model.LevelUiDetails
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
    val levelData by viewModel.levelUiDetails.collectAsStateWithLifecycle()
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
                    viewModel.submitEvent(com.gamovation.core.domain.R.string.event_inapp_review)
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
                LevelScreenState.LevelCompleted -> LevelScreenViewModel.UiState.OnForward
                is LevelScreenState.UserWatchAd -> LevelScreenViewModel.UiState.OnWatchAd
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
            details = levelData,
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
        if (screenState == LevelScreenState.IsLevelPlaying) {
            viewModel.updateUiState(LevelScreenViewModel.UiState.OnBack)
        }
    }
}

@Composable
fun LevelScreenContent(
    details: LevelUiDetails?,
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
            val (levelSpecificContentRef, actionBarRef) = createRefs()

            val bottomGuideLine = createGuidelineFromBottom(0.2f)

            details?.let {
                LevelSpecificContent(
                    modifier = Modifier.constrainAs(levelSpecificContentRef) {
                        width = Dimension.matchParent
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(bottomGuideLine)
                    },
                    details = it,
                    onLevelScreenAction = onUpdateLevelScreenState,
                    onReviewRequest = onReviewRequest,
                    onLevelAction = onUpdateLevelActionState
                )
            }

            ActionBar(
                modifier = Modifier
                    .constrainAs(actionBarRef) {
                        width = Dimension.matchParent
                        height = Dimension.fillToConstraints
                        top.linkTo(bottomGuideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = Dimensions.Padding.Medium.value)
                    },
                onUpdateLevelActionState = onUpdateLevelActionState
            )
        }

        details?.let {
            ActionBarDialog(
                details = it,
                onActionResult = onActionResult
            )
        }

        LevelUserChoiceAlert(
            currentState = screenState
        )
    }
}
