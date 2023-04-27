package com.conboi.feature.menu

import androidx.lifecycle.ViewModel
import com.conboi.core.data.repository.OfflineLevelDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val levelDataRepositoryImpl: OfflineLevelDataRepository
) : ViewModel() {
    fun getLastUncompletedLevel() = levelDataRepositoryImpl.getLastUncompletedLevel()
    fun getLevelDataListByIndex(page: Int) = levelDataRepositoryImpl.getLevelDataListByIndex(page)
}