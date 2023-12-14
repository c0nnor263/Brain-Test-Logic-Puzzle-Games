package com.gamovation.core.ui.state

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.gamovation.core.data.AdRetryPolicy
import com.gamovation.core.data.wasLoadTimeLessThanLimitHoursAgo
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun rememberBannerAdViewState(
    adUnitID: String, context: Context = LocalContext.current
): BannerAdViewState {
    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val adSize = remember(deviceCurrentWidth) {
        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
            context, deviceCurrentWidth
        )
    }
    return remember {
        BannerAdViewState(
            calculatedAdSize = adSize,
            bannerAdUnitID = adUnitID,
        )
    }
}

class BannerAdViewState(
    private val calculatedAdSize: AdSize,
    private val bannerAdUnitID: String
) {
    private var isBannerViewInitialized: Boolean = false
    private val retryPolicy = AdRetryPolicy()
    private var lastLoadTime: Long = 0


    private val bannerAdListener: (AdView) -> AdListener = { adView ->
        object : AdListener() {
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                retryPolicy.retry {
                    loadAd(adView)
                }
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                lastLoadTime = System.currentTimeMillis()
                retryPolicy.reset()
            }
        }
    }

    fun loadAd(adView: AdView) {
        if (wasLoadTimeLessThanLimitHoursAgo(lastLoadTime, 1)) {
            return
        }

        val adRequest = AdRequest.Builder().build()
        adView.apply {
            if (!isBannerViewInitialized) {
                setAdSize(calculatedAdSize)
                adUnitId = bannerAdUnitID
                isBannerViewInitialized = true
            }
            adListener = bannerAdListener(this)
            loadAd(adRequest)
        }
    }
}
