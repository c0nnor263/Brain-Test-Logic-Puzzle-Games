package com.gamovation.core.ui.advertising

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
import com.gamovation.core.ui.BuyButton
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.R

@Composable
fun WatchStoreButton(
    modifier: Modifier = Modifier,
    @StringRes rewardStringRes: Int,
    @StringRes watchStringRes: Int,
    isLoaded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(2f), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.present),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(Dimensions.Padding.Small.value))
            Text(
                text = stringResource(id = rewardStringRes),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(Dimensions.Padding.ExtraSmall.value)
            )
        }

        BuyButton(
            modifier = Modifier.weight(1.5f, false),
            isLoaded = isLoaded,
            text = stringResource(id = watchStringRes),
            onClick = {
                if (isLoaded) {
                    onClick()
                }
            }
        )
    }
}
