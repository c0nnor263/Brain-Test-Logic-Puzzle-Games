package com.conboi.feature.menu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.level.MAX_LEVEL_ID
import com.conboi.core.ui.level.NavigationArrows

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(onNavigateToLevel: (Int) -> Unit) {
    var currentListPageIndex by remember { mutableStateOf(1) }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Small.value),
                contentPadding = PaddingValues(Dimensions.Padding.Small.value),
            ) {

                items(5, key = { it + currentListPageIndex }) {
                    val levelId = it + 1 + 5 * (currentListPageIndex - 1)

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color.Transparent),
                        shape = Dimensions.RoundedShape.Large.value,
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            MaterialTheme.colorScheme.primaryContainer,
                                            MaterialTheme.colorScheme.secondaryContainer,
                                        ),
                                        start = Offset(50.0F, 0.0F),
                                        end = Offset(0.0F, 50.0F),
                                    ),
                                ),
                            onClick = {
                                onNavigateToLevel(levelId)
                            }
                        ) {
                            Text(
                                text = "LEVEL $levelId",

                                style = MaterialTheme.typography.displaySmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(Dimensions.Padding.ExtraLarge.value))
            NavigationArrows(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                currentIndex = currentListPageIndex,
                maxIndex = 10,
                onIndexUpdate = {
                    currentListPageIndex = it.coerceAtMost(MAX_LEVEL_ID)
                }
            )
        }

    }
}


@Preview(device = "spec:width=1920dp,height=1080dp,dpi=160")
@Preview(device = "spec:width=1280dp,height=800dp,dpi=240")
@Preview(device = "spec:width=673dp,height=841dp")
@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun MenuScreenPreview() {
    MenuScreen(onNavigateToLevel = {

    })
}