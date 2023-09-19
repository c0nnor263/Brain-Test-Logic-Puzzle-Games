package com.gamovation.tilecl.di.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class MobileAdsInitializer : Initializer<MobileAdsInitializer> {
    private val configuration = RequestConfiguration.Builder()
        .setTestDeviceIds(
            listOf(
                "56BE866E8D6B7D8AF4EC0676F9236973",
                "B8422D142A4DEF246360E184189A5634"
            )
        )
        .build()

    override fun create(context: Context): MobileAdsInitializer {
        return MobileAdsInitializer().also {
            MobileAds.initialize(context)
            MobileAds.setRequestConfiguration(configuration)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}