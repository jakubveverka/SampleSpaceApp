package com.jakubveverka.samplespaceapp.navigation

import kotlinx.coroutines.flow.StateFlow

interface NavigationManager {

    val destinationState: StateFlow<Screen?>
    fun navigate(screen: Screen)
}