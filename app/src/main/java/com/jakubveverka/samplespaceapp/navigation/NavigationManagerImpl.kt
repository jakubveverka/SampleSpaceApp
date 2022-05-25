package com.jakubveverka.samplespaceapp.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationManagerImpl : NavigationManager {

        private val _destinationState = MutableStateFlow<Screen?>(null)
        override val destinationState = _destinationState.asStateFlow()

        override fun navigate(screen: Screen) {
            _destinationState.value = screen
        }
}