package com.jakubveverka.spacelist.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.model.Launch
import com.jakubveverka.spacedata.repository.SpaceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LaunchListViewModel(private val spaceRepository: SpaceRepository) : ViewModel() {

    val launches: StateFlow<ApiResult<List<Launch>>> = spaceRepository.getLaunches()
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading(listOf()))
}