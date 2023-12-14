package com.gamovation.feature.menu.presentation

import androidx.lifecycle.ViewModel
import com.gamovation.core.data.repository.LevelManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val levelDataRepositoryImpl: LevelManagerRepository
) : ViewModel() {
    fun getAllLevels() = levelDataRepositoryImpl.getAll()
}
