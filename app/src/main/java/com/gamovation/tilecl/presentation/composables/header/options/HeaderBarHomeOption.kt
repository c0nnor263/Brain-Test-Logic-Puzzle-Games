package com.gamovation.tilecl.presentation.composables.header.options

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.tilecl.presentation.composables.header.common.HeaderBarButton

@Composable
fun HeaderBarHomeOption(
    onNavigateToSettings: () -> Unit,
    onNavigateToMenu: () -> Unit
) {
    HeaderBarButton(
        iconRes = R.drawable.baseline_settings_24,
        onClick = onNavigateToSettings
    )
    Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
    HeaderBarButton(
        modifier = Modifier.semantics {
            contentDescription = "MenuScreenNavigate"
        },
        iconRes = R.drawable.baseline_more_horiz_24,
        onClick = onNavigateToMenu
    )
}
