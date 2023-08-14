package com.gamovation.core.ui.store

import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.Durations
import com.gamovation.core.ui.R
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.state.rememberRewardedInterstitialAdViewState


@Composable
fun WatchAdDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissed: (Boolean?) -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity
    val rewardedInterstitialAd = rememberRewardedInterstitialAdViewState(activity)

    var showErrorDialog by remember { mutableStateOf(false) }

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


        Dialog(onDismissRequest = { onDismissed(null) }) {
            ChalkBoardCard(modifier = Modifier.padding(Dimensions.Padding.Small.value)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Do you want to get more",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Yellow),
                            modifier = Modifier.weight(0.7F),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.lamp),
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

                    DrawAnimation {
                        Text(text = "Watch!", modifier = Modifier.clickable {
                            rewardedInterstitialAd.showAd(activity) {
                                if (it == false) {
                                    showErrorDialog = true
                                } else {
                                    onDismissed(it)
                                }
                            }
                        }, style = MaterialTheme.typography.displaySmall)

                    }
                }
            }
        }

    }

    WatchAdErrorDialog(visible = showErrorDialog, onDismissed = {
        showErrorDialog = false
        onDismissed(it)
    })
}