package com.gamovation.core.data.advertising

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class MobileAdsInitializer {
    companion object {
        private val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(
                listOf(
                   "605294fe-166c-4a6f-a59e-f35f680865f3"
                )
            )

            // TODO: Add your test device ID here
            .build()

        fun init(context: Context) {
            MobileAds.initialize(context)
            MobileAds.setRequestConfiguration(configuration)
        }
    }
}