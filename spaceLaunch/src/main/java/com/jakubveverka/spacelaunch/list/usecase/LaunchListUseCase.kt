package com.jakubveverka.spacelaunch.list.usecase

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchListUseCase {

    fun getLaunches(): Flow<ApiResult<List<Launch>>>
}