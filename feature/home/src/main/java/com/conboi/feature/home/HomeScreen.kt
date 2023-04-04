package com.conboi.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.conboi.core.ui.Dimensions

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigateToLevel: (Int) -> Unit
) {

    val lastUncompletedLevel = viewModel.getFirstUncompletedLevelId().collectAsState(initial = null)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.Padding.Medium.value),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Are you ready to show your intelligence?",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(Dimensions.Padding.ExtraLarge.value))
        TextButton(onClick = {
            val id = lastUncompletedLevel.value?.id ?: 1
            onNavigateToLevel(id)
        }) {
            Text(text = "LET'S GO!", style = MaterialTheme.typography.displayLarge)
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HomeScreenPreview() {
    HomeScreen {

    }
}