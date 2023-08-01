package com.conboi.core.ui.billing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.conboi.core.ui.R
import com.conboi.core.ui.extensions.clickableNoRipple

@Composable
fun BuyButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(modifier = modifier.clickableNoRipple {
        onClick()
    }, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.orange_button),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall
        )
    }
}