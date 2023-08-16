package com.gamovation.tilecl.di

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordefullApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.getInitializationStatus().also {
            Log.i("TAG", "onCreate: $it")
        }
    }
}