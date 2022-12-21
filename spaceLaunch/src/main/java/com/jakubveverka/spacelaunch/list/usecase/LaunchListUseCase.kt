package com.jakubveverka.spacelaunch.list.usecase

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import kotlinx.coroutines.flow.Flow

interface LaunchListUseCase {

    fun getLaunches(): Flow<ApiResult<List<LaunchUi>>>
}