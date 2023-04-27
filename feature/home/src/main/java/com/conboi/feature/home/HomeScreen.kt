package com.conboi.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.animation.DrawAnimation
import com.conboi.core.ui.theme.WordefullTheme

@Composable
fun HomeScreen(
    onNavigateToLevel: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(Dimensions.Padding.ExtraLarge.value))
        DrawAnimation {
            Text(
                text = "Are you ready to show your intelligence?",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }

        DrawAnimation(delay = Durations.Medium.time.toLong()) {
            TextButton(onClick = {
                onNavigateToLevel()
            }) {
                Text(text = "LET'S GO!", style = MaterialTheme.typography.displayLarge)
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeScreenPreview() {
    WordefullTheme {
        HomeScreen {

        }
    }
}