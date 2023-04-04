package com.conboi.wordefull.di

import android.app.Application
import com.conboi.core.data.advertising.MobileAdsInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordefullApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAdsInitializer.init(this)
    }
}