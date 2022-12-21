package com.jakubveverka.spacelaunch.list.usecase

import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.domain.model.Launch
import com.jakubveverka.spacedata.repository.SpaceRepository
import kotlinx.coroutines.flow.Flow

class LaunchListUseCaseImpl(private val spaceRepository: SpaceRepository) : LaunchListUseCase {

    override fun getLaunches(): Flow<ApiResult<List<Launch>>> = spaceRepository.getLaunches()
}