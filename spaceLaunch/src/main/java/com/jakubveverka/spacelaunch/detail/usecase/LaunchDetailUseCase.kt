package com.jakubveverka.spacelaunch.detail.usecase

import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import kotlinx.coroutines.flow.Flow

interface LaunchDetailUseCase {

    fun getLaunch(id: String): Flow<LaunchUi>
}