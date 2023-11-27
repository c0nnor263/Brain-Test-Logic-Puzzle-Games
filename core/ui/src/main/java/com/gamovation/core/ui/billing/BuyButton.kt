package com.gamovation.core.ui.billing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.extensions.clickableNoRipple

@Composable
fun BuyButton(
    modifier: Modifier = Modifier,
    isLoaded: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.clickableNoRipple {
            onClick()
        },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .defaultMinSize(
                    minHeight = Dimensions.Padding.ExtraLarge.value
                ),
            painter = painterResource(id = R.drawable.orange_button),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        if (isLoaded) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                textAlign = TextAlign.Center
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(16.dp),
                strokeWidth = 2.dp
            )
        }
    }
}
