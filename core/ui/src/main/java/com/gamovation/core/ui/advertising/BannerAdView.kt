package com.gamovation.core.ui.advertising

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(
    modifier: Modifier = Modifier,
    adUnitId: String = stringResource(id = com.gamovation.core.domain.R.string.test_ad_unit),
) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp

    AndroidView(modifier = modifier, factory = { context: Context ->
        val request = AdRequest.Builder().build()
        AdView(context).apply {
            setAdSize(
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context, deviceCurrentWidth
                )
            )
            setAdUnitId(adUnitId)
            loadAd(request)
        }
    }, update = { adView ->
        val request = AdRequest.Builder().build()
        adView.loadAd(request)
    })

}

@Preview
@Composable
fun BannerAdViewPreview() {
    BannerAdView()
}