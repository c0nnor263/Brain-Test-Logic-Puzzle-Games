package com.gamovation.feature.level.action_bar.dialog.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation

@Composable
fun ActionBarDialogAdviceOption(onClick: () -> Unit) {
    DrawAnimation {
        Text(
            text = "Nothing else pops into your head?",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
    DrawAnimation(delayOrder = 1) {
        Text(
            text = "Well, you can get a hint",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Yellow),
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(Dimensions.Padding.Large.value))

    DrawAnimation(delayOrder = 2) {
        Text(
            text = "Get hint!",
            modifier = Modifier.clickable(onClick = onClick),
            style = MaterialTheme.typography.displaySmall
        )
    }
}