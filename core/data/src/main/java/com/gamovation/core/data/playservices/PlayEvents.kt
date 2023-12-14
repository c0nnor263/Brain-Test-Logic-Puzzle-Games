package com.gamovation.core.data.playservices

import androidx.activity.ComponentActivity
import com.google.android.gms.games.EventsClient
import com.google.android.gms.games.PlayGames

object PlayEvents {

    lateinit var client: EventsClient

    fun createClient(activity: ComponentActivity) {
        client = PlayGames.getEventsClient(activity)
    }
}
