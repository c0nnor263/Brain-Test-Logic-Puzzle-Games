package com.conboi.core.ui.state

import android.content.Context
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
    activity: ComponentActivity? = null
): InterstitialAdViewState =
    remember {
        InterstitialAdViewState().also { state ->
            activity?.let {
                state.loadAd(activity)
            }
        }
    }


class InterstitialAdViewState(interstitialAd: InterstitialAd? = null) {
    var interstitialAd by mutableStateOf(interstitialAd)
        private set

    fun loadAd(context: Context) {
        Log.i("TAG", "loadAd: $context")
        // TEST AD UNIT ID
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
                    Log.i("TAG", "onAdFailedToLoad: ${error}")
                    interstitialAd = null
                }
            }
        )
    }

    fun showAd(context: ComponentActivity, onDismissed: () -> Unit) {

        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                Log.i("TAG", "onAdDismissedFullScreenContent")
                interstitialAd = null
                onDismissed()
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                Log.i("TAG", "onAdFailedToShowFullScreenContent: $error")
                interstitialAd = null
                onDismissed()
            }
        }

        when ((0..10).random().also {
            Log.i("TAG", "showAd: $it $interstitialAd")
        }) {
            in 0..3 -> {
                interstitialAd?.show(context) ?: onDismissed()
            }

            else -> onDismissed()
        }
    }
}