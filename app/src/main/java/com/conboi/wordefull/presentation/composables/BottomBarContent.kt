package com.conboi.wordefull.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.advertising.BannerAdView

@Composable
fun BottomBarContent(modifier: Modifier = Modifier) {
    val isVisible = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visibleState = isVisible,
        enter = fadeIn(
            animationSpec = tween(Durations.Medium.time)
        ) + slideInVertically(
            animationSpec = tween(
                Durations.Medium.time
            )
        ) { it },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.Padding.ExtraSmall.value)
                .animateContentSize(tween(Durations.Medium.time)),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        ) {
            BannerAdView(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


@Preview
@Composable
fun WordeBottomAppBarPreview() {
    BottomBarContent()
}