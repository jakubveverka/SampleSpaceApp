package com.jakubveverka.spacelaunch.detail.viewModel

import androidx.lifecycle.ViewModel
import com.jakubveverka.spacedata.domain.model.Launch
import com.jakubveverka.spacelaunch.detail.usecase.LaunchDetailUseCase
import kotlinx.coroutines.flow.Flow

class LaunchDetailViewModel(private val launchDetailUseCase: LaunchDetailUseCase) : ViewModel() {

    fun getLaunch(id: String): Flow<Launch> = launchDetailUseCase.getLaunch(id)
}