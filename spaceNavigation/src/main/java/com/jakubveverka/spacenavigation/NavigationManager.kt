package com.jakubveverka.spacenavigation

import kotlinx.coroutines.flow.StateFlow

interface NavigationManager {

    var currentRoute: String?
    val destinationState: StateFlow<NavDestination?>
    fun navigate(screen: Screen, params: List<Pair<Screen.Parameter, String>> = listOf())

    data class NavDestination(val screen: Screen, val params: List<Pair<Screen.Parameter, String>>) // TODO  query params
}