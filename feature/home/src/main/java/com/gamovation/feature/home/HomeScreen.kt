package com.gamovation.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.theme.WordefullTheme

@Composable
fun HomeScreen(
    onNavigateToLevel: () -> Unit,
) {
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
            TextButton(modifier = Modifier.semantics {
                contentDescription = "LevelScreenFastNavigate"
            }, onClick = {
                onNavigateToLevel()
            }) {
                Text(
                    text = stringResource(R.string.let_s_go),
                    style = MaterialTheme.typography.displayMedium
                )
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