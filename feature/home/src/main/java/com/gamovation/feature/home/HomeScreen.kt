package com.gamovation.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gamovation.core.database.data.LevelManager.Companion.MAX_LEVEL_ID
import com.gamovation.core.ui.BuyButton
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.VipCrown
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.common.ScalableButton

const val LevelScreenFastNavigateContentDescription = "LevelScreenFastNavigate"

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onNavigateToLevel: (Int) -> Unit,
    onNavigateToStore: () -> Unit
) {
    val levelData = viewModel.getLastUncompleted().collectAsStateWithLifecycle(initialValue = null)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier.fillMaxSize(),
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
            ScalableButton(
                modifier = Modifier.semantics {
                    contentDescription = LevelScreenFastNavigateContentDescription
                },
                delayOrder = 1,
                stringRes = R.string.let_s_go,
                onClick = {
                    val data = levelData.value
                    val levelId = data?.id
                    val isCompleted = data?.isCompleted ?: false
                    val idArg = when (levelId) {
                        MAX_LEVEL_ID - 1 -> if (isCompleted.not()) levelId else 1
                        else -> levelId ?: 1
                    }
                    onNavigateToLevel(idArg)
                }
            )
        }

        GetVipBanner(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNavigateToStore = onNavigateToStore
        )
    }
}

@Composable
fun GetVipBanner(modifier: Modifier = Modifier, onNavigateToStore: () -> Unit) {
    Column(
        modifier = modifier.padding(vertical = Dimensions.Padding.ExtraLarge.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VipCrown()
        Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
        BuyButton(
            text = stringResource(com.gamovation.core.ui.R.string.go),
            isLoaded = true,
            isDrawingAnimationEnabled = true,
            onClick = onNavigateToStore
        )
    }
}
