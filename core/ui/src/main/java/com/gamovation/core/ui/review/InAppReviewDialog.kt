package com.gamovation.core.ui.review

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.Durations
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardCard


@Composable
fun InAppReviewDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onStartReview: () -> Unit,
    onDismiss: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter =
        scaleIn(tween(Durations.Short.time)) +
                expandVertically(tween(Durations.Short.time)) { it } + expandHorizontally(
            tween(
                Durations.ShortMedium.time
            )
        ) { it },
        exit =
        shrinkVertically(tween(Durations.Short.time)) { it } + shrinkHorizontally(
            tween(
                Durations.ShortMedium.time
            )
        ) { it } + scaleOut(tween(Durations.Short.time)),
    ) {


        Dialog(onDismissRequest = { onDismiss() }) {
            ChalkBoardCard(modifier = Modifier.padding(Dimensions.Padding.Small.value)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DrawAnimation {
                        Text(
                            text = "Did you like the game?",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

                    DrawAnimation(delayOrder = 1) {
                        Text(
                            text = "Could you share your feedback so we can work on improving it?",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DrawAnimation(delayOrder = 2) {
                            Text(text = "Yes", modifier = Modifier.clickable {
                                onStartReview()
                            }, style = MaterialTheme.typography.headlineMedium)
                        }
                        Spacer(Modifier.width(Dimensions.Padding.Small.value))
                        DrawAnimation(delayOrder = 3) {
                            Text(text = "No", modifier = Modifier.clickable {
                                onDismiss()
                            }, style = MaterialTheme.typography.headlineMedium)

                        }
                    }

                }
            }
        }

    }
}