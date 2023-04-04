package com.conboi.core.data.advertising

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class MobileAdsInitializer {
    companion object {
        private val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(listOf("DEVICE ID"))
            .build()

        fun init(context: Context) {
            MobileAds.initialize(context)

            MobileAds.setRequestConfiguration(configuration)
        }
    }
}