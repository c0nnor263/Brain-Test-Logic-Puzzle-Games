package com.conboi.feature.level.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
        maxLines = 3,
        textAlign = TextAlign.Center
    )
}