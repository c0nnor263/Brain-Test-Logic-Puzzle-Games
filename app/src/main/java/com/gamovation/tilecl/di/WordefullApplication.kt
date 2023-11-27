package com.gamovation.tilecl.di

import android.app.Application
import com.gamovation.tilecl.R
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WordefullApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(
            this,
            getString(R.string.one_signal_key)
        )
    }
}
