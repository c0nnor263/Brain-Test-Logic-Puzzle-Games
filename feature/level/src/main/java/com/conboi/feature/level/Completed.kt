package com.conboi.feature.level

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.R
import com.conboi.core.ui.store.WatchAdDialog
import com.conboi.core.ui.store.WatchStoreItem
import com.conboi.core.ui.theme.WordefullTheme
import com.conboi.feature.level.common.Title

@Composable
fun Completed(
    modifier: Modifier = Modifier,
    id: Int,
    onLevelUIAction: (LevelScreenState) -> Unit
) {
    var showWatchAdDialog by remember { mutableStateOf(false) }
    AnimatedVisibility(
        true,
        modifier = modifier.padding(Dimensions.Padding.Small.value),
        enter = fadeIn(tween(Durations.Medium.time)) + scaleIn(tween(Durations.Medium.time)),
        exit = scaleOut(tween(Durations.Medium.time)) + fadeOut(tween(Durations.Medium.time)),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lamp),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
            Title(
                text = "Level $id completed",
                style = MaterialTheme.typography.displaySmall
            )
            Title(
                text = "You awesome!",
                style = MaterialTheme.typography.displaySmall.copy(color = Color.Yellow)
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = {
                    onLevelUIAction(LevelScreenState.NEXT_LEVEL)
                }) {
                    Text("Next Level", style = MaterialTheme.typography.headlineMedium)
                }
                Spacer(modifier = Modifier.height(Dimensions.Padding.Medium.value))
                WatchStoreItem(value = "x25", text = "Watch Ad") {
                    showWatchAdDialog = true
                }
            }
        }

    }

    WatchAdDialog(visible = showWatchAdDialog, onDismissed = { result ->
        showWatchAdDialog = false
        if (result == true) onLevelUIAction(LevelScreenState.WATCH_AD)
    })
}


@Preview
@Composable
fun CompletedPreview() {
    WordefullTheme {
        Completed(id = 1, onLevelUIAction = {})
    }
}