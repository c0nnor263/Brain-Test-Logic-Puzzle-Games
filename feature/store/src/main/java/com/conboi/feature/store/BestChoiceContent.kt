package com.conboi.feature.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import com.android.billingclient.api.ProductDetails
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.common.ChalkBoardCard
import com.conboi.core.ui.theme.boardBorderColor
import kotlin.math.roundToInt

@Composable
fun BestChoiceContent(
    modifier: Modifier = Modifier,
    bestChoiceDetails: ProductDetails?,
    onClick: () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            colors = CardDefaults.cardColors(boardBorderColor),
            modifier = Modifier
                .zIndex(2F)
                .offset {
                    IntOffset(0, +Dimensions.Padding.Large.value.value.roundToInt())
                }) {
            Text(
                text = "Best Choice",
                modifier = Modifier.padding(Dimensions.Padding.Medium.value),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
        ChalkBoardCard(modifier = Modifier.zIndex(1F), color = Color.White) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BuyButton(
                    price = bestChoiceDetails?.oneTimePurchaseOfferDetails?.formattedPrice
                        ?: "",
                    onClick = onClick
                )
            }
        }
    }
}