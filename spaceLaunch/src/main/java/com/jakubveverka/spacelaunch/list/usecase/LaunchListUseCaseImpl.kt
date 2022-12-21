package com.jakubveverka.spacelaunch.list.usecase

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.api.transformList
import com.jakubveverka.spacedata.repository.SpaceRepository
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import com.jakubveverka.spacelaunch.uimodel.toUIModel
import kotlinx.coroutines.flow.Flow

class LaunchListUseCaseImpl(private val spaceRepository: SpaceRepository) : LaunchListUseCase {

    override fun getLaunches(): Flow<ApiResult<List<LaunchUi>>> = spaceRepository.getLaunches().transformList { it.toUIModel() }
}