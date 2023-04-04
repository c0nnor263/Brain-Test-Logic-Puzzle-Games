package com.conboi.feature.level.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.conboi.core.ui.level.MAX_LEVEL_ID
import com.conboi.core.ui.level.NavigationArrows

@Composable
fun LevelNavigationBar(
    modifier: Modifier = Modifier,
    currentLevelId: Int,
    onLevelUpdate: (Int) -> Unit
) {

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {


        NavigationArrows(
            currentIndex = currentLevelId,
            maxIndex = MAX_LEVEL_ID,
            onIndexUpdate = onLevelUpdate
        ) {
            Text(
                text = "Level $currentLevelId",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }

}