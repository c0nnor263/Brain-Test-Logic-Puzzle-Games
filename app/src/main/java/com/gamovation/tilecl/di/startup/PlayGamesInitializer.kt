package com.gamovation.tilecl.di.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.android.gms.games.PlayGamesSdk

class PlayGamesInitializer : Initializer<PlayGamesInitializer> {

    override fun create(context: Context): PlayGamesInitializer {
        return PlayGamesInitializer().also {
            PlayGamesSdk.initialize(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
