package com.gamovation.feature.store.items

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamovation.core.billing.model.StoreItemInfo
import com.gamovation.core.billing.model.isNotEmpty
import com.gamovation.core.billing.model.price
import com.gamovation.core.ui.BuyButton
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R

@Composable
fun StoreItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int = R.drawable.lamp,
    @StringRes stringRes: Int,
    info: StoreItemInfo? = null,
    onBuy: (StoreItemInfo) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(2f), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = drawableRes),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            Text(
                text = stringResource(id = stringRes),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .weight(1F, false)
                    .padding(Dimensions.Padding.ExtraSmall.value)
            )
        }

        BuyButton(
            modifier = Modifier.weight(1.5f, false),
            text = info.price(),
            isLoaded = info.isNotEmpty(),
            onClick = {
                info?.let(onBuy)
            }
        )
    }
}
