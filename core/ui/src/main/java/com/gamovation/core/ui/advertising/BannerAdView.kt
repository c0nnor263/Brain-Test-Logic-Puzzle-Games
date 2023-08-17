package com.gamovation.core.ui.advertising

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.gamovation.core.data.advertising.AdRequestBuilder
import com.gamovation.core.domain.advertising.TEST_AD_UNIT_ID
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(
    modifier: Modifier = Modifier,
    adUnitId: String = TEST_AD_UNIT_ID
) {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val adRequest by remember {
        mutableStateOf(AdRequestBuilder().createDefault().also {
            Log.i("TAG", "BannerAdView: ${it.contentUrl} ${it.adString}")
        })
    }
    AndroidView(modifier = modifier, factory = { context: Context ->
        AdView(context).apply {
            setAdSize(
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context, deviceCurrentWidth
                )
            )
            setAdUnitId(adUnitId)
            loadAd(adRequest)
        }
    }, update = { adView ->
        adView.loadAd(adRequest)
    })

}

@Preview
@Composable
fun BannerAdViewPreview() {
    BannerAdView()
}