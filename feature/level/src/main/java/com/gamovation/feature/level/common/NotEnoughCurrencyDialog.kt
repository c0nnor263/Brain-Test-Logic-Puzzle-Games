package com.gamovation.feature.level.common

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gamovation.core.domain.level.ActionResult
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.extensions.clickableNoRipple
import com.gamovation.feature.level.R

@Composable
fun NotEnoughCurrencyDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onActionResult: (ActionResult) -> Unit
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
        Dialog(onDismissRequest = {}) {
            ChalkBoardCard(modifier = Modifier.padding(Dimensions.Padding.Small.value)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DrawAnimation {
                        Text(
                            text = stringResource(
                                R.string.you_don_t_have_enough_currency_to_get_a_hint
                            ),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.do_you_want_to_get_more),
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Yellow),
                            modifier = Modifier.weight(0.7F),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            painter = painterResource(id = com.gamovation.core.ui.R.drawable.lamp),
                            modifier = Modifier.weight(0.1F),
                            contentDescription = null
                        )
                        Text(
                            text = "?",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Yellow),
                            modifier = Modifier.weight(0.2F),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))

                    DrawAnimation(delayOrder = 1) {
                        Text(
                            stringResource(R.string.get_more),
                            Modifier.clickableNoRipple {
                                onActionResult(ActionResult(ActionResult.Type.BUY_MORE))
                            },
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
            }
        }
    }
}
