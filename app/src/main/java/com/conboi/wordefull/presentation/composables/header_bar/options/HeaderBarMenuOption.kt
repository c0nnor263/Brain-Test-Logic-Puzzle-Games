package com.conboi.wordefull.presentation.composables.header_bar.options

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.R

@Composable
fun RowScope.HeaderBarMenuOption(
    onNavigateBack: () -> Unit,
) {
    IconButton(onClick = onNavigateBack) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.button_icon_bg),
            contentDescription = null
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_left_24),
            contentDescription = null
        )
    }

}