package com.jakubveverka.samplespaceapp.ui.composable

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.Screen

@Composable
fun MyTopBar(navigationManager: NavigationManager, modifier: Modifier = Modifier, iconClick: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Space App") },
        navigationIcon = {
            IconButton(onClick = iconClick) {
                Icon(navigationManager.destinationState.value?.screen?.toTopBarIcon() ?: Icons.Rounded.Menu, contentDescription = "TopBarIcon")
            }
        }
    )
}

private fun Screen.toTopBarIcon(): ImageVector {
//    return when (this) {
//        Screen.LaunchDetail -> Icons.Rounded.ArrowBack
//        Screen.LaunchList -> Icons.Rounded.Menu
//        Screen.Screen2 -> Icons.Rounded.Menu
//        Screen.Screen3 -> Icons.Rounded.Menu
//    }
    return Icons.Rounded.Menu
}