package com.gamovation.feature.level.actionbar.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.extensions.clickableNoRipple

@Composable
fun AdviceDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    advice: String,
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
        ) { it } + scaleOut(tween(Durations.Short.time))
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
                            text = advice,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

                    DrawAnimation(delayOrder = 1) {
                        Text(
                            text = stringResource(com.gamovation.core.ui.R.string.thanks),
                            modifier = Modifier.clickableNoRipple {
                                onDismiss()
                            },
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}
