package com.conboi.feature.level.action_bar.dialog.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.animation.DrawAnimation

@Composable
fun ActionBarDialogSkipOption(onClick: () -> Unit) {
    DrawAnimation {
        Text(
            text = "Do you really want to skip this level?",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
    DrawAnimation(delay = Durations.Medium.time.toLong()) {
        Text(
            text = "This level will let you do this",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Yellow),
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))

    DrawAnimation(delay = Durations.Medium.time.toLong() * 2) {
        Text(
            text = "Skip!",
            modifier = Modifier.clickable(onClick = onClick),
            style = MaterialTheme.typography.displaySmall
        )
    }
}