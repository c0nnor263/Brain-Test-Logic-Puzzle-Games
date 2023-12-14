package com.gamovation.core.ui.advertising

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.gamovation.core.ui.state.rememberBannerAdViewState
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(
    modifier: Modifier = Modifier,
    adUnitId: String = stringResource(id = com.gamovation.core.domain.R.string.test_ad_unit)
) {
    val bannerAdViewState =
        rememberBannerAdViewState(adUnitID = adUnitId)

    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).also {
                bannerAdViewState.loadAd(it)
            }
        },
        update = { adView ->
            bannerAdViewState.loadAd(adView)
        }
    )
}

@Preview
@Composable
fun BannerAdViewPreview() {
    BannerAdView()
}
