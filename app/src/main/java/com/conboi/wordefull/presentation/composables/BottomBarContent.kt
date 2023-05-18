package com.conboi.wordefull.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.domain.billing.UserVipType
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.Durations
import com.conboi.core.ui.advertising.BannerAdView
import com.conboi.core.ui.state.LocalVipType
import com.conboi.core.ui.theme.boardBorderColor

@Composable
fun BottomBarContent(modifier: Modifier = Modifier) {
    val vipType = LocalVipType.current
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = vipType == UserVipType.BASE,
        enter = fadeIn(
            animationSpec = tween(Durations.Medium.time)
        ) + slideInVertically(
            animationSpec = tween(
                Durations.Medium.time
            )
        ) { it },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = Dimensions.Padding.Small.value, color = boardBorderColor),
            contentAlignment = Alignment.Center
        ) {
            BannerAdView(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun WordeBottomAppBarPreview() {
    BottomBarContent()
}