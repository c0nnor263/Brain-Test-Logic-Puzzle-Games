package com.gamovation.tilecl.presentation.composables.header_bar.options

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.header_bar.common.HeaderBarButton

@Composable
fun RowScope.HeaderBarSettingsOption(
    onNavigateBack: () -> Unit,
) {
    HeaderBarButton(iconRes = R.drawable.baseline_arrow_left_24, onClick = onNavigateBack)
}
