package com.conboi.core.ui.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.conboi.core.data.advertising.AdRequestBuilder
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


@Composable
fun rememberInterstitialAdViewState(
    activity: ComponentActivity,
): InterstitialAdViewState =
    remember {
        InterstitialAdViewState().also { state ->
            state.loadAd(activity)
        }
    }


class InterstitialAdViewState(interstitialAd: InterstitialAd? = null) {
    var interstitialAd by mutableStateOf(interstitialAd)
        private set

    fun loadAd(context: ComponentActivity) {
        Log.i("TAG", "loadAd: $context")
        // TODO TEST AD UNIT ID
        val adRequest = AdRequestBuilder().createDefault()
        InterstitialAd.load(
            context,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    super.onAdLoaded(ad)
                    Log.i("TAG", "onAdLoaded: ${ad.responseInfo}")
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    interstitialAd = null
                }
            }
        )
    }

    fun showAd(activity: ComponentActivity, onDismissed: () -> Unit = {}) {

        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                interstitialAd = null

                onDismissed()
                loadAd(activity)
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                interstitialAd = null
                loadAd(activity)
                onDismissed()
            }
        }

        when ((0..10).random()) {
            in 0..4 -> {
                interstitialAd?.show(activity) ?: onDismissed()
            }

            else -> onDismissed()
        }
    }
}