package com.gamovation.feature.menu.presentation

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gamovation.core.database.data.LevelManager.Companion.MAX_LEVEL_ID
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.common.ScalableButton

@Composable
fun NavigationArrows(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    onIndexUpdate: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            Dimensions.Padding.Medium.value,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = currentIndex != 0,
            enter = fadeIn() + scaleIn(),
            exit = scaleOut() + fadeOut()
        ) {
            ArrowButton(
                drawableRes = com.gamovation.core.ui.R.drawable.baseline_arrow_left_24,
                onClick = {
                    val newId = currentIndex - 5
                    onIndexUpdate(newId.coerceAtLeast(0))
                }
            )
        }

        AnimatedVisibility(
            visible = currentIndex != MAX_LEVEL_ID - 5,
            enter = fadeIn() + scaleIn(),
            exit = scaleOut() + fadeOut()
        ) {
            ArrowButton(
                drawableRes = com.gamovation.core.ui.R.drawable.baseline_arrow_right_24,
                onClick = {
                    val newId = currentIndex + 5
                    onIndexUpdate(newId.coerceAtMost(MAX_LEVEL_ID - 5))
                }
            )
        }
    }
}

@Composable
fun ArrowButton(@DrawableRes drawableRes: Int, onClick: () -> Unit) {
    ScalableButton(
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painterResource(id = com.gamovation.core.ui.R.drawable.orange_button),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
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
        currentIndex = 1,
        onIndexUpdate = {}
    )
}
