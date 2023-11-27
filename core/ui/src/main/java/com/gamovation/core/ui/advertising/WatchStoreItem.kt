package com.gamovation.core.ui.advertising

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.billing.BuyButton

@Composable
fun WatchStoreItem(
    modifier: Modifier = Modifier,
    value: String,
    text: String,
    isLoaded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1F), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.present),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1F, false)
            )
        }

        BuyButton(
            modifier = Modifier
                .weight(1F, false)
                .drawWithContent {
                    drawContent()
                    if (!isLoaded) {
                        drawRoundRect(Color.Gray.copy(0.5F), cornerRadius = CornerRadius(28F, 28F))
                    }
                },
            isLoaded = isLoaded,
            text = text,
            onClick = {
                if (isLoaded) {
                    onClick()
                }
            }
        )
    }
}
