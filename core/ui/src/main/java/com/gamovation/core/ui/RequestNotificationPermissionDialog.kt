package com.gamovation.core.ui

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.extensions.clickableNoRipple

const val RequestNotificationPermissionYesTag = "RequestNotificationPermissionYesTag"

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestNotificationPermissionDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    val requestPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        onDismiss()
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = scaleIn(tween(Durations.Short.time)) + expandVertically(tween(Durations.Short.time)) {
            it
        } + expandHorizontally(
            tween(
                Durations.ShortMedium.time
            )
        ) { it },
        exit = shrinkVertically(tween(Durations.Short.time)) { it } + shrinkHorizontally(
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
                            text = stringResource(
                                R.string.request_notification_permission_title
                            ),
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))

                    DrawAnimation(delayOrder = 1) {
                        Text(
                            text = stringResource(
                                R.string.request_notification_permission_message,
                                stringResource(
                                    id = R.string.app_name
                                )
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
                                modifier = Modifier.semantics {
                                    contentDescription = RequestNotificationPermissionYesTag
                                }.clickableNoRipple {
                                    requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
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
    }
}
