package com.gamovation.feature.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.database.data.LevelManager.Companion.MAX_LEVEL_ID
import com.gamovation.core.database.preferences.UserInfoPreferencesDataStore.Companion.DEFAULT_NOTIFICATION_PERMISSION_LIMIT
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.RequestNotificationPermissionDialog
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.core.ui.state.rememberDialogState

const val LevelScreenFastNavigateContentDescription = "LevelScreenFastNavigate"

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onNavigateToLevel: (Int) -> Unit
) {
    val levelData = viewModel.getLastUncompleted().collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current
    val dialogState = rememberDialogState()

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tryCount = viewModel.getNotificationPermissionTryCount()
            if (tryCount > DEFAULT_NOTIFICATION_PERMISSION_LIMIT) {
                return@LaunchedEffect
            }

            if (context.checkSelfPermission(
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_DENIED
            ) {
                dialogState.show()
                viewModel.increaseNotificationRequestCount()
            } else {
                if (tryCount != 0) {
                    viewModel.resetNotificationRequestCount()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(Dimensions.Padding.ExtraLarge.value))
        DrawAnimation {
            Text(
                text = stringResource(R.string.are_you_ready_to_show_your_intelligence),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }
        Spacer(modifier = Modifier.padding(Dimensions.Padding.Medium.value))
        ScalableButton(
            modifier = Modifier.semantics {
                contentDescription = LevelScreenFastNavigateContentDescription
            },
            delayOrder = 1,
            stringRes = R.string.let_s_go,
            onClick = {
                val data = levelData.value
                val levelId = data?.id
                val isCompleted = data?.isCompleted ?: false
                val idArg = when (levelId) {
                    MAX_LEVEL_ID - 1 -> if (isCompleted.not()) levelId else 1
                    else -> levelId ?: 1
                }
                onNavigateToLevel(idArg)
            }
        )
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        RequestNotificationPermissionDialog(
            dialogState = dialogState,
            onDismiss = {
                dialogState.dismiss()
            }
        )
    }
}
