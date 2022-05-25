package com.jakubveverka.samplespaceapp.menu

import com.jakubveverka.samplespaceapp.navigation.Screen

enum class MenuItem(val title: String, val screen: Screen) {
    MENU_ITEM_1("Menu item 1", Screen.Screen1),
    MENU_ITEM_2("Menu item 2", Screen.Screen2),
    MENU_ITEM_3("Menu item 3", Screen.Screen3)
}
