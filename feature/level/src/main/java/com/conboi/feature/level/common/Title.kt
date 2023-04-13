package com.conboi.feature.level.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun Title(
    currentLevelId: Int,
    style: TextStyle = MaterialTheme.typography.headlineMedium
) {
    val title = when (currentLevelId) {
        1 -> "How many ducks?"
        2 -> "Wake up the owl"
        3 -> "Which one is not ice cream?"
        4 -> "Light up the 4th bulb"
        5 -> "You have to win the game"
        else -> ""
    }
    Text(
        text = title,
        style = style.copy(fontWeight = FontWeight.Bold),
        maxLines = 3,
        textAlign = TextAlign.Center
    )
}