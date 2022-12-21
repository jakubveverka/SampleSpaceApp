package com.jakubveverka.spacelaunch.detail.usecase

import com.jakubveverka.spacedata.repository.SpaceRepository
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import com.jakubveverka.spacelaunch.uimodel.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaunchDetailUseCaseImpl(private val spaceRepository: SpaceRepository) : LaunchDetailUseCase {

    override fun getLaunch(id: String): Flow<LaunchUi> = spaceRepository.getLaunch(id).map { it.toUIModel() }
}