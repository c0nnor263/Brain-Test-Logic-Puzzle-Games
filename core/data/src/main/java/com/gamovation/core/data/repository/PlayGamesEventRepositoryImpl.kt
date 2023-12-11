package com.gamovation.core.data.repository

import android.content.Context
import androidx.annotation.StringRes
import com.gamovation.core.data.playservices.PlayEvents
import com.gamovation.core.domain.repository.PlayGamesEventRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PlayGamesEventRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayGamesEventRepository {
    override fun submitEvent(@StringRes eventId: Int, value: Int) {
        val eventName = context.getString(eventId)
        PlayEvents
            .client
            .increment(eventName, value)
    }
}
