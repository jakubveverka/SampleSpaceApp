package com.jakubveverka.spacelaunch.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.domain.model.Launch
import com.jakubveverka.spacelaunch.list.usecase.LaunchListUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LaunchListViewModel(launchListUseCase: LaunchListUseCase) : ViewModel() {

    val launches: StateFlow<ApiResult<List<Launch>>> = launchListUseCase.getLaunches()
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading(listOf()))
}