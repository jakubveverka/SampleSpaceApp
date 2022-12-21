package com.jakubveverka.spacelaunch.detail.usecase

import com.jakubveverka.spacedata.domain.model.Launch
import com.jakubveverka.spacedata.repository.SpaceRepository
import kotlinx.coroutines.flow.Flow

class LaunchDetailUseCaseImpl(private val spaceRepository: SpaceRepository) : LaunchDetailUseCase {

    override fun getLaunch(id: String): Flow<Launch> = spaceRepository.getLaunch(id)
}