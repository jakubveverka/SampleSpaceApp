package com.jakubveverka.samplespaceapp.menu

import com.jakubveverka.spacenavigation.Screen

enum class MenuItem(val title: String, val screen: Screen) {
    LAUNCH_LIST("Launch List", Screen.LaunchList),
    MENU_ITEM_2("Menu item 2", Screen.Screen2),
    MENU_ITEM_3("Menu item 3", Screen.Screen3)
}
