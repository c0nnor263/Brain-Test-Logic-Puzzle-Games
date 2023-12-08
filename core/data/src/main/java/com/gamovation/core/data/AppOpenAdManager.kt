package com.gamovation.core.data

import android.app.Activity
import android.content.Context
import android.util.Log
import com.gamovation.core.domain.AdStatus
import com.gamovation.core.domain.di.ApplicationScope
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppOpenAdManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope
) {
    companion object {
        private const val DEFAULT_LOAD_RETRY = 3
    }

    private val _adStatus = MutableStateFlow(AdStatus.LOADING)

    private var appOpenAd: AppOpenAd? = null
    private var loadRetry = DEFAULT_LOAD_RETRY
    private var loadTime = 0L
    private var isLoadingAd = false
    private var isShowingAd = false

    init {
        loadAd()
    }

    private fun loadAd() {
        if (isLoadingAd || isAdAvailable()) {
            return
        }
        isLoadingAd = true
        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            context,
            context.getString(R.string.admob_app_open_from_push),
            request,
            object : AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    super.onAdLoaded(ad)
                    updateAppOpenAd(ad, AdStatus.LOADED)
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    Log.i("TAG", "onAdFailedToLoad: $error")
                    updateAppOpenAd(null, AdStatus.FAILED)
                }
            }
        )
    }

    fun showAdIfAvailable(activity: Activity) {
        if (isShowingAd) {
            return
        }
        if (!isAdAvailable()) {
            loadAd()
            return
        }

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                updateAppOpenAd(null, AdStatus.DISSMISSED)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                updateAppOpenAd(null, AdStatus.FAILED_TO_SHOW)
            }
        }
        isShowingAd = true
        appOpenAd?.show(activity)
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null &&
            !wasLoadTimeLessThanLimitHoursAgo(loadTime, 4)
    }

    private fun updateAppOpenAd(ad: AppOpenAd?, adStatus: AdStatus) {
        Log.i("TAG", "updateAppOpenAd: $adStatus")
        when (adStatus) {
            AdStatus.LOADED -> {
                appOpenAd = ad
                isLoadingAd = false
                loadRetry = DEFAULT_LOAD_RETRY
                loadTime = System.currentTimeMillis()
            }

            AdStatus.FAILED_TO_SHOW, AdStatus.DISSMISSED -> {
                appOpenAd = ad
                isShowingAd = false
                loadAd()
            }

            AdStatus.FAILED -> {
                isLoadingAd = false
                if (loadRetry > 0) {
                    loadRetry--
                    loadAd()
                }
            }

            else -> {}
        }
        _adStatus.value = adStatus
    }
}
