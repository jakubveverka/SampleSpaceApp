package com.jakubveverka.samplespaceapp.navigation

sealed class Screen(val route: String) {

    object Screen1 : Screen("screen1")
    object Screen2 : Screen("screen2")
    object Screen3 : Screen("screen3")
}