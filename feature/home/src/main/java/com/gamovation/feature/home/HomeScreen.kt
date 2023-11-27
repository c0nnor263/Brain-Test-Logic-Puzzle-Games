package com.gamovation.feature.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.database.preferences.UserInfoPreferencesDataStore.Companion.DEFAULT_NOTIFICATION_PERMISSION_LIMIT
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.RequestNotificationPermissionDialog
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onNavigateToLevel: () -> Unit
) {
    val context = LocalContext.current
    var showRequestNotificationPermission by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val tryCount = viewModel.getNotificationPermissionTryCount()
            if (tryCount > DEFAULT_NOTIFICATION_PERMISSION_LIMIT) {
                return@LaunchedEffect
            }

            if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                showRequestNotificationPermission = true
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
        verticalArrangement = Arrangement.Top,
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
        DrawAnimation(delayOrder = 1) {
            TextButton(
                modifier = Modifier.semantics {
                    contentDescription = "LevelScreenFastNavigate"
                },
                onClick = {
                    onNavigateToLevel()
                }
            ) {
                Text(
                    text = stringResource(R.string.let_s_go),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        RequestNotificationPermissionDialog(
            visible = showRequestNotificationPermission,
            onDismiss = {
                showRequestNotificationPermission = false
            }
        )
    }
}
