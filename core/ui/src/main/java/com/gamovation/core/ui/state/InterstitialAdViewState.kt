package com.gamovation.core.ui.state

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gamovation.core.data.advertising.AdRequestBuilder
import com.gamovation.core.domain.billing.UserVipType
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


@Composable
fun rememberInterstitialAdViewState(
    activity: ComponentActivity,
    adUnitID:String,
    userVipType: UserVipType = UserVipType.BASE
): InterstitialAdViewState =
    remember {
        InterstitialAdViewState(userVipType,adUnitID).also { state ->
            state.loadAd(activity)
        }
    }


class InterstitialAdViewState(
    private val userVipType: UserVipType,
    private val adUnitID: String,
    interstitialAd: InterstitialAd? = null
) {
    var interstitialAd by mutableStateOf(interstitialAd)
        private set

    fun loadAd(context: ComponentActivity) {
        if (userVipType != UserVipType.BASE) return

        val adRequest = AdRequestBuilder().createDefault()
        InterstitialAd.load(
            context,
            adUnitID,
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