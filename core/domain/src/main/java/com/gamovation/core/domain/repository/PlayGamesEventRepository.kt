package com.gamovation.core.domain.repository

import androidx.annotation.StringRes

interface PlayGamesEventRepository {
    fun submitEvent(@StringRes eventId: Int, value: Int)
}
