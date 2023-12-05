package com.gamovation.feature.menu.presentation

import androidx.lifecycle.ViewModel
import com.gamovation.core.data.repository.OfflineLevelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val levelDataRepositoryImpl: OfflineLevelDataRepository
) : ViewModel() {
    fun getAllLevels() = levelDataRepositoryImpl.getAll()
}
