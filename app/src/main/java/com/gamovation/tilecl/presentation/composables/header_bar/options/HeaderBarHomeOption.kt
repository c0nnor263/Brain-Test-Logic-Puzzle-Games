package com.gamovation.tilecl.presentation.composables.header_bar.options

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.header_bar.common.HeaderBarButton

@Composable
fun RowScope.HeaderBarHomeOption(
    onNavigateToSettings: () -> Unit,
    onNavigateToMenu: () -> Unit,
) {
    HeaderBarButton(
        iconRes = R.drawable.baseline_settings_24,
        onClick = onNavigateToSettings
    )
    Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
    HeaderBarButton(iconRes = R.drawable.baseline_more_horiz_24, onClick = onNavigateToMenu)
}