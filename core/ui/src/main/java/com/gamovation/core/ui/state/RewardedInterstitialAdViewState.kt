package com.gamovation.core.ui.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gamovation.core.data.advertising.AdRequestBuilder
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback


@Composable
fun rememberRewardedInterstitialAdViewState(
    activity: ComponentActivity, adUnitID: String
): RewardedInterstitialAdViewState =
    remember {
        RewardedInterstitialAdViewState(adUnitID = adUnitID).also { state ->
            state.loadAd(activity)
        }
    }


class RewardedInterstitialAdViewState(
    rewardedInterstitialAd: RewardedInterstitialAd? = null,
    private val adUnitID: String
) {
    var rewardedInterstitialAd by mutableStateOf(rewardedInterstitialAd)
        private set

    fun loadAd(context: ComponentActivity) {

        val adRequest = AdRequestBuilder().createDefault()
        RewardedInterstitialAd.load(
            context,
            adUnitID,
            adRequest,
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    super.onAdLoaded(ad)
                    Log.i("TAG", "onAdLoaded: ${ad.responseInfo}")
                    rewardedInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    Log.i("TAG", "onAdFailedToLoad: ${error.responseInfo}")
                    rewardedInterstitialAd = null
                }
            }
        )
    }

    fun showAd(activity: ComponentActivity, onDismissed: (result: Boolean?) -> Unit = {}) {

        rewardedInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                rewardedInterstitialAd = null
                loadAd(activity)
                Log.i("TAG", "onAdDismissedFullScreenContent: ")
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                rewardedInterstitialAd = null
                loadAd(activity)
                onDismissed(false)
                Log.i("TAG", "onAdFailedToShowFullScreenContent: ${error.message} ")
            }

        }

        rewardedInterstitialAd?.show(activity) {
            onDismissed(true)
        } ?: onDismissed(null)
    }
}