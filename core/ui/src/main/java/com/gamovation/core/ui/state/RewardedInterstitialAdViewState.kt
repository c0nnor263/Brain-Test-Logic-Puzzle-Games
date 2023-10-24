package com.gamovation.core.ui.state

import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
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
    val isAdLoaded
        get() = rewardedInterstitialAd != null

    fun loadAd(context: ComponentActivity) {

        val adRequest = AdRequest.Builder().build()
        RewardedInterstitialAd.load(
            context,
            adUnitID,
            adRequest,
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    super.onAdLoaded(ad)
                    rewardedInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    rewardedInterstitialAd = null
                    Handler(Looper.getMainLooper()).postDelayed({
                        loadAd(context)
                    }, 5000)
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
                onDismissed(null)
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                rewardedInterstitialAd = null
                loadAd(activity)
                onDismissed(false)
            }


        }

        rewardedInterstitialAd?.show(activity) {
            onDismissed(true)
        } ?: onDismissed(null)
    }
}