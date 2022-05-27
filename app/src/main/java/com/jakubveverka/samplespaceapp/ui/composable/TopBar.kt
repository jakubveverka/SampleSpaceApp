package com.jakubveverka.samplespaceapp.ui.composable

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyTopBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    TopAppBar(
        title = { Text(text = "Space App") },
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(Icons.Rounded.Menu, contentDescription = "Menu")
            }
        }
    )
}