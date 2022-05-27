package com.jakubveverka.spacenavigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationManagerImpl : NavigationManager {

    private val _destinationState = MutableStateFlow<NavigationManager.NavDestination?>(null)
    override var currentRoute: String? = null
    override val destinationState = _destinationState.asStateFlow()

    override fun navigate(screen: Screen, params: List<Pair<Screen.Parameter, String>>) {
        _destinationState.value = NavigationManager.NavDestination(screen, params)
    }
}