package com.jakubveverka.spacenavigation

sealed class Screen(val route: String, val parameters: List<Parameter> = listOf()) {

    object LaunchList : Screen("launch_list")
    object Screen2 : Screen("screen2")
    object Screen3 : Screen("screen3")
    object LaunchDetail: Screen("launch_detail/{launchId}", listOf(Parameter.LAUNCH_ID))

    enum class Parameter(val paramName: String, val type: Class<out Any>, val defaultValue: Any? = null) {
        LAUNCH_ID("launchId", String::class.java)
    }
}