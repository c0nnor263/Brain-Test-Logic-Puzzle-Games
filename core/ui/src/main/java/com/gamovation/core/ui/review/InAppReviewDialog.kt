package com.gamovation.core.ui.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.clickableNoRipple
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.state.DialogState

@Composable
fun InAppReviewDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onStartReview: () -> Unit,
    onDismiss: () -> Unit
) {
    ChalkBoardDialog(
        modifier = modifier,
        dialogState = dialogState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawAnimation {
                Text(
                    text = stringResource(R.string.did_you_like_the_game),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

            DrawAnimation(delayOrder = 1) {
                Text(
                    text = stringResource(
                        R.string.could_you_share_your_feedback_so_we_can_work_on_improving_it
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DrawAnimation(delayOrder = 2) {
                    Text(
                        text = stringResource(R.string.yes),
                        modifier = Modifier.clickableNoRipple {
                            onStartReview()
                        },
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(Modifier.width(Dimensions.Padding.Small.value))
                DrawAnimation(delayOrder = 3) {
                    Text(
                        text = stringResource(R.string.no),
                        modifier = Modifier.clickableNoRipple {
                            onDismiss()
                        },
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}
