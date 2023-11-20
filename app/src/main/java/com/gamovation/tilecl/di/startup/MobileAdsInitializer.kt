package com.gamovation.tilecl.di.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE

class MobileAdsInitializer : Initializer<MobileAdsInitializer> {
    private val configuration = RequestConfiguration.Builder()
        .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
        .build()

    override fun create(context: Context): MobileAdsInitializer {
        return MobileAdsInitializer().also {
            MobileAds.setRequestConfiguration(configuration)
            MobileAds.initialize(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}