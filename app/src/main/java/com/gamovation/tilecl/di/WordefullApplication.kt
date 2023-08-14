package com.gamovation.tilecl.di

import android.app.Application
import com.gamovation.core.data.advertising.MobileAdsInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordefullApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAdsInitializer.init(this)
    }
}