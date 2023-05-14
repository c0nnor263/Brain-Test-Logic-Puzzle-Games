package com.conboi.core.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.conboi.core.domain.advertising.DEFAULT_SHARE_COST
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R

@Composable
fun ShareButton(modifier: Modifier = Modifier, @DrawableRes drawableRes: Int, onClick: () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = R.drawable.o_mark),
            contentDescription = null
        )
        IconButton(
            modifier = Modifier.padding(Dimensions.Padding.Medium.value),
            onClick = onClick
        ) {
            Icon(painter = painterResource(id = drawableRes), contentDescription = null)
        }
        Text(
            "+$DEFAULT_SHARE_COST",
            modifier = Modifier.align(Alignment.BottomEnd),
            style = MaterialTheme.typography.titleLarge
        )
    }
}