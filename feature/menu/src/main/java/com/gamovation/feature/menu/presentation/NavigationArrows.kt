package com.gamovation.feature.menu.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.database.data.LevelManager.Companion.MAX_LEVEL_ID
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.common.ScalableButton

@Composable
fun NavigationArrows(
    modifier: Modifier = Modifier,
    itemsCount: Int,
    page: Int,
    onIndexUpdate: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            Dimensions.Padding.Medium.value,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        ArrowButton(
            modifier = Modifier.weight(1F),
            drawableRes = com.gamovation.core.ui.R.drawable.baseline_arrow_left_24,
            enabled = page != 0 && itemsCount > 5,
            onClick = {
                val newId = page - 1
                onIndexUpdate(newId.coerceAtLeast(0))
            }
        )

        ArrowButton(
            modifier = Modifier.weight(1F),
            drawableRes = com.gamovation.core.ui.R.drawable.baseline_arrow_right_24,
            enabled = page != MAX_LEVEL_ID / 5 && itemsCount > page.plus(1) * 5,
            onClick = {
                val newId = page + 1
                onIndexUpdate(newId.coerceAtMost(MAX_LEVEL_ID - 1))
            }
        )
    }
}

@Composable
fun ArrowButton(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    enabled: Boolean,
    onClick: () -> Unit
) {
    ScalableButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painterResource(id = com.gamovation.core.ui.R.drawable.orange_button),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                colorFilter = if (enabled) null else ColorFilter.tint(Color.Gray.copy(0.7F))
            )
            Icon(
                painter = painterResource(id = drawableRes),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun NavigationArrowsPreview() {
    NavigationArrows(
        itemsCount = 10,
        page = 1,
        onIndexUpdate = {}
    )
}
