package com.conboi.wordefull.presentation.composables.header_bar.options

import androidx.compose.runtime.Composable
import com.conboi.core.ui.R
import com.conboi.wordefull.presentation.composables.header_bar.common.HeaderBarButton

@Composable
fun HeaderBarStoreOption(
    onNavigateBack: () -> Unit,
) {
    HeaderBarButton(iconRes = R.drawable.baseline_arrow_left_24, onClick = onNavigateBack)

}