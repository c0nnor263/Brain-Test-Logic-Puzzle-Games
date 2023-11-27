package com.gamovation.tilecl.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.domain.billing.UserVipType
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.advertising.BannerAdView
import com.gamovation.core.ui.animation.Durations
import com.gamovation.core.ui.state.LocalVipType
import com.gamovation.core.ui.theme.WordefullTheme
import com.gamovation.core.ui.theme.boardBorderColor

@Composable
fun BottomBarContent(modifier: Modifier = Modifier) {
    val vipType = LocalVipType.current
    AnimatedVisibility(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        visible = vipType == UserVipType.BASE,
        enter = fadeIn(
            animationSpec = tween(Durations.Medium.time)
        ) + slideInVertically(
            animationSpec = tween(
                Durations.Medium.time
            )
        ) { it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = Dimensions.Padding.ExtraSmall.value, color = boardBorderColor),
            contentAlignment = Alignment.Center
        ) {
            BannerAdView(
                modifier = Modifier.fillMaxWidth(),
                stringResource(
                    id = com.gamovation.core.data.R.string.admob_banner_id_app_bottom_banner
                )
            )
        }
    }
}

@Preview
@Composable
fun WordeBottomAppBarPreview() {
    WordefullTheme {
        BottomBarContent()
    }
}
