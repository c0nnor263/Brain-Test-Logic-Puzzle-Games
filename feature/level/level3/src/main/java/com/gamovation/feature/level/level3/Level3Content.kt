package com.gamovation.feature.level.level3

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
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

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()

    ) {
        val (
            iceCream1,
            iceCream2,
            iceCream3,
            iceCream4,
            iceCream5,
            iceCream6,
            sun
        ) = createRefs()

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

        listOf(
            iceCream1,
            iceCream2,
            iceCream3,
            iceCream4,
            iceCream5,
            iceCream6
        ).forEachIndexed { index, reference ->
            Level3IceCream(
                modifier = Modifier.constrainAs(reference) {
                    width = Dimension.fillToConstraints
                    when (index) {
                        in 0..2 -> {
                            top.linkTo(sun.top, margin = Dimensions.Padding.ExtraLarge2X.value)
                        }

                        in 3..5 -> {
                            top.linkTo(
                                iceCream2.bottom,
                                margin = Dimensions.Padding.Medium.value
                            )
                        }
                    }
                },
                index = index,
                transition = meltingTransition,
                isNotIceCream = index == 4
            ) {
                val newState =
                    if (meltingTransition.currentState && index == 4) {
                        LevelScreenState.UserCorrectChoice(
                            com.gamovation.core.domain.R.string.event_level_3_finished
                        )
                    } else {
                        LevelScreenState.UserWrongChoice(
                            com.gamovation.core.domain.R.string.event_level_3_wrong
                        )
                    }
                onLevelAction(newState)
            }
        }

        DraggableImage(
            modifier = Modifier
                .constrainAs(sun) {
                    top.linkTo(parent.top, margin = -Dimensions.Padding.Large.value)
                    start.linkTo(parent.start, margin = -Dimensions.Padding.ExtraLarge2X.value)
                },
            isEnabled = !meltingTransition.currentState,
            drawableRes = com.gamovation.core.ui.R.drawable.sun
        ) { sunOffset, screenSize ->
            if (sunOffset.x >= screenSize.x / 2) {
                isMelting = true
            }
        }
    }

    InAppReviewDialog(
        dialogState = showReviewDialogState,
        onStartReview = {
            showReviewDialogState.dismiss()
            onReviewRequest()
        },
        onDismiss = {
            showReviewDialogState.dismiss()
        }
    )
}
