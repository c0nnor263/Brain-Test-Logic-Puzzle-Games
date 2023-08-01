package com.conboi.feature.store.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.common.ChalkBoardCard

@Composable
fun VipContent(onBuy: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ChalkBoardCard(
            modifier = Modifier.weight(1F, false), color = Color.White.copy(alpha = 0.7F)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "BECOME A VIP!")
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(
                    text = "Remove ads and get cheaper costs for all actions!",
                    maxLines = 4,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                TextButton(onClick = { onBuy() }) {
                    Text(
                        text = "VIP", style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))

        Image(
            painter = painterResource(id = R.drawable.crown),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            contentDescription = null,
        )

    }
}