package com.conboi.feature.store.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.ProductDetails
import com.conboi.core.data.price
import com.conboi.core.ui.Dimensions
import com.conboi.core.ui.R
import com.conboi.core.ui.billing.BuyButton
import com.conboi.core.ui.theme.WordefullTheme

@Composable
fun StoreItem(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int = R.drawable.lamp,
    value: String,
    details: ProductDetails? = null,
    onClick: (ProductDetails) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1F), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = drawableRes),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1F, false)
            )
        }

        BuyButton(modifier = Modifier.weight(1F, false), text = details.price(), onClick = {
            details?.let(onClick)
        })
    }
}

@Preview
@Composable
fun StoreItemPreview() {
    WordefullTheme {
        StoreItem(value = "10") {

        }
    }
}