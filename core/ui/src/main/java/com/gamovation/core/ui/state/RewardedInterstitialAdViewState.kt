package com.gamovation.core.ui.state

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.gamovation.core.data.AdRetryPolicy
import com.gamovation.core.data.wasLoadTimeLessThanLimitHoursAgo
import com.gamovation.core.domain.enums.RewardedInterstitialAdResult
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun rememberRewardedInterstitialAdViewState(
    activity: ComponentActivity,
    adUnitID: String
): RewardedInterstitialAdViewState {
    val scope = rememberCoroutineScope()
    return remember {
        RewardedInterstitialAdViewState(
            adUnitID = adUnitID,
            scope = scope
        ).also { state ->
            state.loadOrGetAd(activity)
        }
    }
}

class RewardedInterstitialAdViewState(
    private val scope: CoroutineScope,
    private val adUnitID: String
) {
    private val retryPolicy = AdRetryPolicy()
    var rewardedInterstitialAd by mutableStateOf<RewardedInterstitialAd?>(null)
        private set
    val isAdAvailable
        get() = rewardedInterstitialAd != null

    fun loadOrGetAd(activity: ComponentActivity) {
        scope.launch {
            RewardedInterstitialAdManager
                .loadAd(activity, adUnitID)
                .fold(
                    onSuccess = { ad ->
                        rewardedInterstitialAd = ad
                        retryPolicy.reset()
                    },
                    onFailure = {
                        retryPolicy.retry {
                            loadOrGetAd(activity)
                        }
                    }
                )
        }
    }

    fun showAd(
        activity: ComponentActivity,
        onDismissed: (result: RewardedInterstitialAdResult) -> Unit = {}
    ) {
        rewardedInterstitialAd?.fullScreenContentCallback =
            object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    rewardedInterstitialAd = null
                    RewardedInterstitialAdManager.reset()
                    loadOrGetAd(activity)
                    onDismissed(RewardedInterstitialAdResult.DISMISSED)
                }

                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    super.onAdFailedToShowFullScreenContent(error)
                    rewardedInterstitialAd = null
                    RewardedInterstitialAdManager.reset()
                    loadOrGetAd(activity)
                    onDismissed(RewardedInterstitialAdResult.ERROR)
                }
            }

        rewardedInterstitialAd?.show(activity) {
            onDismissed(RewardedInterstitialAdResult.REWARDED)
        } ?: onDismissed(RewardedInterstitialAdResult.DISMISSED)
    }
}

object RewardedInterstitialAdManager {

    private var lastLoadedAd: RewardedInterstitialAd? = null
    private var lastLoadTime: Long = 0
    private val isAdAvailable
        get() = lastLoadedAd != null

    suspend fun loadAd(
        context: Context,
        adUnitID: String
    ) =
        suspendCoroutine { continuation ->
            if (wasLoadTimeLessThanLimitHoursAgo(lastLoadTime, 1) && isAdAvailable) {
                continuation.resume(Result.success(lastLoadedAd))
                return@suspendCoroutine
            }

            val adRequest = AdRequest.Builder().build()
            RewardedInterstitialAd.load(
                context,
                adUnitID,
                adRequest,
                object : RewardedInterstitialAdLoadCallback() {
                    override fun onAdLoaded(ad: RewardedInterstitialAd) {
                        super.onAdLoaded(ad)
                        lastLoadTime = System.currentTimeMillis()
                        lastLoadedAd = ad
                        continuation.resume(Result.success(ad))
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        super.onAdFailedToLoad(error)
                        val adException = Exception(error.message)
                        val result = Result.failure<RewardedInterstitialAd>(adException)
                        continuation.resume(result)
                    }
                }
            )
        }

    fun reset() {
        lastLoadedAd = null
    }
}


