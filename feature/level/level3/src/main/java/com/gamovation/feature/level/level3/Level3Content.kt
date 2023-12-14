package com.gamovation.feature.level.level3

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.InAppReviewDialog
import com.gamovation.core.ui.level.interactions.DraggableImage
import com.gamovation.core.ui.level.interactions.DraggableOrientation
import com.gamovation.core.ui.state.LocalReviewDataHandlerState
import com.gamovation.core.ui.state.rememberDialogState

@Composable
fun Level3Content(
    modifier: Modifier = Modifier,
    onReviewRequest: () -> Unit,
    onLevelAction: (LevelScreenState) -> Unit
) {
    val reviewDataHandlerState = LocalReviewDataHandlerState.current
    val showReviewDialogState = rememberDialogState()

    LaunchedEffect(reviewDataHandlerState.isGranted) {
        if (!reviewDataHandlerState.isGranted) {
            showReviewDialogState.show()
        }
    }

    var isMelting by rememberSaveable { mutableStateOf(false) }
    val meltingTransition = updateTransition(targetState = isMelting, label = "")

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (iceCream1, iceCream2, iceCream3, iceCream4, iceCream5, iceCream6, sun) = createRefs()

        createHorizontalChain(
            iceCream1,
            iceCream2,
            iceCream3
        )

        createHorizontalChain(
            iceCream4,
            iceCream5,
            iceCream6
        )

        val topGuideline = createGuidelineFromTop(0.5F)

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream1) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(topGuideline)
            },
            index = 0,
            transition = meltingTransition,
            isNotIceCream = false
        ) {
            val newState = LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_3_wrong
            )
            onLevelAction(newState)
        }

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream2) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topGuideline)
            },
            index = 1,
            transition = meltingTransition,
            isNotIceCream = false
        ) {
            val newState = LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_3_wrong
            )
            onLevelAction(newState)
        }

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream3) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(topGuideline)
            },
            index = 2,
            transition = meltingTransition,
            isNotIceCream = false
        ) {
            val newState = LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_3_wrong
            )
            onLevelAction(newState)
        }

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream4) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            },
            index = 3,
            transition = meltingTransition,
            isNotIceCream = false
        ) {
            val newState = LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_3_wrong
            )
            onLevelAction(newState)
        }

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream5) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topGuideline)
                bottom.linkTo(parent.bottom)
            },
            index = 4,
            transition = meltingTransition,
            isNotIceCream = true
        ) {
            if (meltingTransition.currentState) {
                val newState = LevelScreenState.UserCorrectChoice(
                    com.gamovation.core.domain.R.string.event_level_3_finished
                )
                onLevelAction(newState)
            }
        }

        Level3IceCream(
            modifier = Modifier.constrainAs(iceCream6) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topGuideline)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            index = 5,
            transition = meltingTransition,
            isNotIceCream = false
        ) {
            val newState = LevelScreenState.UserWrongChoice(
                com.gamovation.core.domain.R.string.event_level_3_wrong
            )
            onLevelAction(newState)
        }

        DraggableImage(
            modifier = Modifier.constrainAs(sun) {
                top.linkTo(parent.top, margin = -Dimensions.Padding.Large.value)
                start.linkTo(parent.start, margin = -Dimensions.Padding.ExtraLarge2X.value)
            },
            orientation = DraggableOrientation.Horizontal,
            isEnabled = !meltingTransition.currentState,
            drawableRes = com.gamovation.core.ui.R.drawable.sun
        ) { sunOffset, screenSize ->
            if (sunOffset.x >= screenSize.x / 2) {
                isMelting = true
            }
        }
    }

    InAppReviewDialog(dialogState = showReviewDialogState, onStartReview = {
        showReviewDialogState.dismiss()
        onReviewRequest()
    }, onDismiss = {
        showReviewDialogState.dismiss()
    })
}
