package com.jakubveverka.spacelaunch.detail.usecase

import com.jakubveverka.spacedata.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchDetailUseCase {

    fun getLaunch(id: String): Flow<Launch>
}