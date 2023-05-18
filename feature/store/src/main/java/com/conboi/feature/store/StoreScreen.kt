package com.conboi.feature.store

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.conboi.core.data.billing.InAppProductsIDs
import com.conboi.core.data.billing.ProductDetailsInfo
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.theme.WordefullTheme

@Composable
fun StoreScreen(productDetailsInfo: ProductDetailsInfo?, onClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val coolestOfferDetails =
        productDetailsInfo?.inAppDetails?.also { Log.i("TAG", "StoreScreen: $it") }
            ?.find { it.productId == InAppProductsIDs.coolestOffer }
    val bestChoiceDetails =
        productDetailsInfo?.inAppDetails?.find { it.productId == InAppProductsIDs.bestOffer }

    val currencyPriceList = productDetailsInfo?.inAppDetails
        ?.filter {

            it.productId == InAppProductsIDs.currency250 ||
                    it.productId == InAppProductsIDs.currency500 ||
                    it.productId == InAppProductsIDs.currency1000 ||
                    it.productId == InAppProductsIDs.removeAds
        }
        ?.sortedBy { it.productId }
        ?.map { it.oneTimePurchaseOfferDetails?.formattedPrice }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = Dimensions.Padding.Small.value),
            verticalArrangement = Arrangement.spacedBy(Dimensions.Padding.Medium.value)
        ) {
            item {
                VipContent()
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CoolestOfferContent(modifier = Modifier.weight(0.5F), coolestOfferDetails) {

                    }
                    Spacer(modifier = Modifier.width(Dimensions.Padding.Medium.value))
                    BestChoiceContent(modifier = Modifier.weight(0.5F), bestChoiceDetails) {

                    }
                }
            }

            item {
                StoreItem(
                    value = "remove ads",
                    buttonText = currencyPriceList?.getOrNull(3) ?: ""
                ) {

                }
            }
            item {
                StoreItem(
                    value = "x25",
                    buttonText = "watch"
                ) {

                }
            }
            item {
                StoreItem(value = "x250", buttonText = currencyPriceList?.getOrNull(0) ?: "") {

                }
            }
            item {
                StoreItem(value = "x500", buttonText = currencyPriceList?.getOrNull(1) ?: "") {

                }
            }
            item {
                StoreItem(value = "x1000", buttonText = currencyPriceList?.getOrNull(2) ?: "") {

                }
            }
        }


        // Share options


        // Ad for reward
    }


}

// TODO free item every day


@Preview
@Composable
fun StoreScreenPreview() {
    WordefullTheme {
        StoreScreen(null) {

        }
    }
}