package com.jakubveverka.spacelaunch.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.api.transformList
import com.jakubveverka.spacelaunch.list.usecase.LaunchListUseCase
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import kotlinx.coroutines.flow.*

class LaunchListViewModel(launchListUseCase: LaunchListUseCase) : ViewModel() {

    val launches: StateFlow<ApiResult<List<LaunchUi>>> =
        launchListUseCase.getLaunches().stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading(listOf()))

}

fun Flow<ApiResult<List<LaunchUi>>>.filterByName(filterName: String): Flow<ApiResult<List<LaunchUi>>> =
    transformList { it.filter { it.name.contains(filterName, ignoreCase = true) } }