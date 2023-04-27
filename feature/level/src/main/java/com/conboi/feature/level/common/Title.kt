package com.conboi.feature.level.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun Title(
    text: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium
) {
    Text(
        text = text,
        style = style.copy(fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center
    )
}