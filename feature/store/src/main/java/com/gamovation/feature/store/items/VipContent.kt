package com.gamovation.feature.store.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gamovation.core.data.model.StoreItemInfo
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.billing.BuyButton
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.feature.store.R
import com.gamovation.feature.store.price

@Composable
fun VipContent(info: StoreItemInfo?, onBuy: (StoreItemInfo) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ChalkBoardCard(
            modifier = Modifier.weight(1F),
            color = Color.White.copy(alpha = 0.7F)
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.become_a_vip))
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(
                    text = stringResource(
                        R.string.remove_ads_and_get_cheaper_costs_for_all_actions
                    ),
                    maxLines = 4,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))

        Column(
            modifier = Modifier.weight(1F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.crown),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.get_vip),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
            BuyButton(
                text = info.price(),
                isLoaded = info?.details != null,
                onClick = {
                    info?.let(onBuy)
                }
            )
        }
    }
}
