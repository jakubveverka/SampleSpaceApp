package com.jakubveverka.spacelist.detail.viewModel

import androidx.lifecycle.ViewModel
import com.jakubveverka.spacedata.model.Launch
import com.jakubveverka.spacedata.repository.SpaceRepository
import kotlinx.coroutines.flow.Flow

class LaunchDetailViewModel(private val spaceRepository: SpaceRepository) : ViewModel() {

    fun getLaunch(id: String): Flow<Launch> = spaceRepository.getLaunch(id)
}