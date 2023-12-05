package com.gamovation.feature.store.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gamovation.core.data.model.StoreItemInfo
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R
import com.gamovation.core.ui.billing.BuyButton
import com.gamovation.core.ui.common.ChalkBoardCard
import com.gamovation.core.ui.theme.boardBorderColor
import com.gamovation.feature.store.price
import kotlin.math.roundToInt

@Composable
fun SmartestOfferContent(
    modifier: Modifier = Modifier,
    info: StoreItemInfo?,
    onBuy: (StoreItemInfo) -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            colors = CardDefaults.cardColors(boardBorderColor),
            modifier = Modifier
                .zIndex(2F)
                .offset {
                    IntOffset(0, +Dimensions.Padding.ExtraLarge.value.value.roundToInt())
                }
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(com.gamovation.feature.store.R.string.smartest),
                modifier = Modifier
                    .padding(Dimensions.Padding.Medium.value)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        ChalkBoardCard(
            modifier = Modifier
                .zIndex(1F),
            color = Color.White.copy(alpha = 0.7F)
        ) {
            Column(
                modifier = Modifier.padding(Dimensions.Padding.ExtraSmall.value),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.lamp),
                        modifier = Modifier.size(32.dp),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    Text("x250", style = MaterialTheme.typography.bodyMedium.copy(Color.White))
                    Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
                    Image(
                        painter = painterResource(
                            id = com.gamovation.feature.store.R.drawable.remove_ads
                        ),
                        modifier = Modifier.size(32.dp),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.height(Dimensions.Padding.Small.value))
                BuyButton(
                    text = info?.price(),
                    isLoaded = info?.details != null,
                    onClick = {
                        info?.let {
                            onBuy(info)
                        }
                    }
                )
            }
        }
    }
}
