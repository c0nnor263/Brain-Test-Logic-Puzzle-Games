package com.conboi.feature.home

import androidx.lifecycle.ViewModel
import com.conboi.core.data.repository.OfflineLevelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val levelDataRepositoryImpl: OfflineLevelDataRepository
) : ViewModel() {

    fun getLastUncompletedLevel() = levelDataRepositoryImpl.getLastUncompletedLevel()
}