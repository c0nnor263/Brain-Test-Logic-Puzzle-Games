package com.gamovation.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, button, banner) = createRefs()
        val topGuideline = createGuidelineFromTop(0.5f)

        DrawAnimation(
            modifier = Modifier.constrainAs(title) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = Dimensions.Padding.Small.value)
                centerHorizontallyTo(parent)
                bottom.linkTo(button.top)
            }
        ) {
            Text(
                text = stringResource(R.string.are_you_ready_to_show_your_intelligence),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }
        ScalableButton(
            modifier = Modifier
                .constrainAs(button) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(title.bottom, margin = Dimensions.Padding.Small.value)
                    centerHorizontallyTo(parent)
                    bottom.linkTo(topGuideline)
                }

                .semantics {
                    contentDescription = LevelScreenFastNavigateContentDescription
                },
            appearOrder = 1,
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

        GetVipBanner(
            modifier = Modifier.constrainAs(banner) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(topGuideline)
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom)
            },
            onNavigateToStore = onNavigateToStore
        )
    }
}

@Composable
fun GetVipBanner(modifier: Modifier = Modifier, onNavigateToStore: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VipCrown()
        Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
        BuyButton(
            modifier = Modifier.requiredWidth(200.dp),
            text = stringResource(com.gamovation.core.ui.R.string.go),
            isLoaded = true,
            isDrawingAnimationEnabled = true,
            onClick = onNavigateToStore
        )
    }
}
