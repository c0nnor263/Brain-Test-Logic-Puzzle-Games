package com.gamovation.tilecl.presentation.composables.header.options

import androidx.compose.runtime.Composable
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.header.common.HeaderBarButton

@Composable
fun HeaderBarMenuOption(
    onNavigateBack: () -> Unit
) {
    HeaderBarButton(iconRes = R.drawable.home_icon, onClick = onNavigateBack)
}
