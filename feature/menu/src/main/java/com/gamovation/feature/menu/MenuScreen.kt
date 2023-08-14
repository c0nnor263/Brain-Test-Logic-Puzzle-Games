package com.gamovation.feature.menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.level.NavigationArrows
import com.gamovation.core.ui.theme.WordefullTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    index: Int,
    levelList: List<LevelData>,
    onNavigateToLevel: (Int) -> Unit,
    onIndexUpdate: (Int) -> Unit
) {


    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value),
                contentPadding = PaddingValues(Dimensions.Padding.Small.value),
            ) {

                items(items = levelList, key = { it.id }) {

                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            enabled = it.isLocked.not(),
                            onClick = {
                                onNavigateToLevel(it.id)
                            }
                        ) {
                            Text(
                                text = "LEVEL ${it.id}",
                                color = if (it.isLocked) Color.Gray else Color.White,
                                style = MaterialTheme.typography.displaySmall,
                                textDecoration = if (it.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                            )
                        }
                }
            }

            Spacer(modifier = Modifier.padding(Dimensions.Padding.Medium.value))
            NavigationArrows(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                currentIndex = index,
                onIndexUpdate = onIndexUpdate,
            )
        }

    }
}


@Preview()
@Composable
fun MenuScreenPreview() {
    WordefullTheme {
        MenuScreen(
            levelList = listOf(
                LevelData(id = 1, isCompleted = true, isLocked = false),
                LevelData(id = 2, isCompleted = false, isLocked = false),
                LevelData(id = 3, isCompleted = false),
                LevelData(id = 4, isCompleted = false),
                LevelData(id = 5, isCompleted = false),
            ),
            index = 0,
            onNavigateToLevel = {},
            onIndexUpdate = {}
        )
    }
}