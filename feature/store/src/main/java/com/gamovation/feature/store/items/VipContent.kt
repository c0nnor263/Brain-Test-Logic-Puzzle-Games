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
import androidx.compose.ui.unit.sp
import com.gamovation.core.billing.model.StoreItemInfo
import com.gamovation.core.billing.model.isNotEmpty
import com.gamovation.core.billing.model.price
import com.gamovation.core.ui.BoxWithSparkles
import com.gamovation.core.ui.BuyButton
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.feature.store.R

@Composable
fun VipContent(info: StoreItemInfo?, onBuy: (StoreItemInfo) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ChalkBoardCard(
            modifier = Modifier.weight(0.5F),
            color = Color.White.copy(alpha = 0.7F)
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.Padding.Small.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.become_a_vip),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(
                    text = stringResource(
                        R.string.remove_ads_and_get_cheaper_costs_for_all_actions
                    ),
                    maxLines = 8,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                Text(
                    text = stringResource(id = R.string.vip_description),
                    maxLines = 8,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))

        Column(
            modifier = Modifier.weight(0.5F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithSparkles {
                Image(
                    painter = painterResource(id = com.gamovation.core.ui.R.drawable.crown),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraSmall.value))
            BuyButton(
                text = stringResource(
                    id = R.string.vip_per_week,
                    info.price(isSubscription = true)
                ),
                isLoaded = info.isNotEmpty(),
                onClick = {
                    info?.let(onBuy)
                }
            )
            Text(
                text = stringResource(id = R.string.cancel_anytime),
                maxLines = 6,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = MaterialTheme.colorScheme.tertiary.copy(0.6F)
            )
        }
    }
}
