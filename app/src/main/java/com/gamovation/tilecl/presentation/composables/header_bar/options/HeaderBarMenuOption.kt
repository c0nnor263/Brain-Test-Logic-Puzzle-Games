package com.gamovation.tilecl.presentation.composables.header_bar.options

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.header_bar.common.HeaderBarButton

@Composable
fun RowScope.HeaderBarMenuOption(
    onNavigateBack: () -> Unit,
) {
    HeaderBarButton(iconRes = R.drawable.home_icon, onClick = onNavigateBack)
}
