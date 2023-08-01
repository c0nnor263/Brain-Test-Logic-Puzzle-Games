package com.conboi.feature.level

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.ActionResult
import com.conboi.core.domain.level.LevelActionState
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.level.UserInteractionState
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
    userInteractionState: UserInteractionState?,
    onActionResult: (ActionResult) -> Unit,
) {
    val screenState = LocalLevelScreenState.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(screenState) {
        scope.launch {
            when (screenState) {
                LevelScreenState.COMPLETED -> userInteractionState?.onForward()
                LevelScreenState.WATCH_AD -> userInteractionState?.onWatchAd()
                else -> {}
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (contents, actionBar) = createRefs()

            val bottomGuideLine = createGuidelineFromBottom(0.1f)
            Contents(
                modifier = Modifier.constrainAs(contents) {
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottomGuideLine)
                },
                level = level ?: LevelData(),
                onLevelAction = {
                    userInteractionState?.onUpdateLevelActionState(it)
                },
                onLevelScreenAction = {
                    userInteractionState?.onUpdateLevelScreenState(it)
                }
            )

            ActionBar(
                modifier = Modifier
                    .constrainAs(actionBar) {
                        width = Dimension.matchParent
                        top.linkTo(bottomGuideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = Dimensions.Padding.Medium.value)
                    },
                onRestart = { userInteractionState?.onUpdateLevelActionState(LevelActionState.RESTART) },
                onGetAdvice = { userInteractionState?.onUpdateLevelActionState(LevelActionState.ADVICE) },
                onSkip = { userInteractionState?.onUpdateLevelActionState(LevelActionState.SKIP) }
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
            userInteractionState?.onBack()
        }
    }
}


@Preview
@Composable
fun LevelScreenPreview() {
    WordefullTheme {
        LevelScreen(
            level = LevelData(),
            null,
        ) {

        }
    }
}