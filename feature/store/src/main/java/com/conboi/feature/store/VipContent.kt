package com.conboi.feature.store

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.common.ChalkBoardCard
import com.conboi.core.ui.extensions.clickableNoRipple

@Composable
fun VipContent() {
    ChalkBoardCard(color = Color.White) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.weight(1F, false),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "BECOME A VIP!")
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(text = "Remove ads and get cheaper prices")
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(text = "VIP", modifier = Modifier.clickableNoRipple {
                    // TODO Navigate to VIP screen
                }, style = MaterialTheme.typography.headlineLarge)
            }
            Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
            Image(
                painter = painterResource(id = R.drawable.lamp),
                modifier = Modifier.weight(0.20F),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}