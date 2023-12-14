package com.gamovation.core.ui

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ChalkBoardDialog
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.DialogState

const val RequestNotificationPermissionYesTag = "RequestNotificationPermissionYesTag"

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestNotificationPermissionDialog(
    modifier: Modifier = Modifier,
    dialogState: DialogState,
    onDismiss: () -> Unit
) {
    val requestPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        onDismiss()
    }

    ChalkBoardDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        dialogState = dialogState
    ) {
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

            DrawAnimation(appearOrder = 1) {
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
                ScalableButton(
                    modifier = Modifier
                        .semantics {
                            contentDescription = RequestNotificationPermissionYesTag
                        },
                    appearOrder = 2,
                    stringRes = R.string.yes,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    onClick = {
                        requestPermission.launch(
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    }
                )

                Spacer(Modifier.width(Dimensions.Padding.Small.value))

                ScalableButton(
                    appearOrder = 3,
                    stringRes = R.string.no,
                    textStyle = MaterialTheme.typography.headlineMedium,
                    onClick = onDismiss
                )
            }
        }
    }
}
