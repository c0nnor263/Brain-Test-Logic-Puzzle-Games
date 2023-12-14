package com.gamovation.feature.menu.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.database.data.LevelManager
import com.gamovation.core.database.model.LevelData
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.common.ScalableButton
import com.gamovation.feature.menu.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    onNavigateToLevel: (Int) -> Unit
) {
    var pageIndex by remember {
        mutableIntStateOf(0)
    }
    val allLevels by viewModel.getAllLevels().collectAsStateWithLifecycle(persistentListOf())

    val levelList by remember(allLevels) {
        derivedStateOf {
            val startIndex = pageIndex * 5
            val endIndex = min(startIndex + 5, allLevels.size)
            allLevels.subList(startIndex, endIndex).toPersistentList()
        }
    }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (numberList, navigationBlock) = createRefs()

            LevelNumberList(
                modifier = Modifier.constrainAs(numberList) {
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                    bottom.linkTo(navigationBlock.top)
                },
                list = levelList,
                onNavigateToLevel = onNavigateToLevel
            )
            NavigationArrows(
                modifier = Modifier.constrainAs(navigationBlock) {
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                    top.linkTo(numberList.bottom)
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom)
                },
                page = pageIndex,
                itemsCount = allLevels.size,
                onIndexUpdate = {
                    pageIndex = it.coerceAtMost(LevelManager.MAX_LEVEL_ID)
                }
            )
        }
    }
}

@Composable
fun LevelNumberList(
    modifier: Modifier = Modifier,
    list: ImmutableList<LevelData>,
    onNavigateToLevel: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "MenuLazyColumn"
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(Dimensions.Padding.Small.value)
    ) {
        items(items = list, key = { it.id }) {
            ScalableButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = it.isLocked.not(),
                isDrawingAnimationEnabled = false,
                onClick = {
                    onNavigateToLevel(it.id)
                }
            ) {
                Text(
                    text = stringResource(R.string.level, it.id),
                    color = Color.White,
                    style = MaterialTheme.typography.displaySmall,
                    textDecoration = if (it.isCompleted) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
            }
        }
    }
}
