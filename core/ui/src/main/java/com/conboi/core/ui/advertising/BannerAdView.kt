package com.conboi.core.ui.advertising

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.conboi.core.data.advertising.AdRequestBuilder
import com.conboi.core.domain.advertising.TEST_AD_UNIT_ID
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(modifier: Modifier = Modifier, adUnitId: String = TEST_AD_UNIT_ID) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp

    AndroidView(modifier = modifier, factory = { context: Context ->
        val adRequest = AdRequestBuilder().createDefault()
        AdView(context).apply {
            setAdSize(
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context,
                    deviceCurrentWidth
                )
            )
            setAdUnitId(adUnitId)
            loadAd(adRequest)
        }
    }, update = { adView ->
        val adRequest = AdRequestBuilder().createDefault()
        adView.loadAd(adRequest)
    })

}

@Preview
@Composable
fun BannerAdViewPreview() {
    BannerAdView()
}