package com.gamovation.core.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.theme.boardBackgroundColor
import com.gamovation.core.ui.theme.boardBorderColor

@Composable
fun ChalkBoardCard(
    modifier: Modifier = Modifier,
    color: Color = boardBorderColor,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RectangleShape,
        border = BorderStroke(Dimensions.Padding.Small.value, color),
        colors = CardDefaults.cardColors(boardBackgroundColor)
    ) {
        Box(
            modifier = Modifier.padding(Dimensions.Padding.Small.value),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
