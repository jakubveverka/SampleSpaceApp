package com.jakubveverka.spacelaunch.detail.viewModel

import androidx.lifecycle.ViewModel
import com.jakubveverka.spacelaunch.detail.usecase.LaunchDetailUseCase
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import kotlinx.coroutines.flow.Flow

class LaunchDetailViewModel(private val launchDetailUseCase: LaunchDetailUseCase) : ViewModel() {

    fun getLaunch(id: String): Flow<LaunchUi?> = launchDetailUseCase.getLaunch(id)
}