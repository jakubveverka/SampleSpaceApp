package com.jakubveverka.samplespaceapp.ui.composable

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyTopBar(modifier: Modifier = Modifier, iconClick: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Space App") },
        navigationIcon = {
            IconButton(onClick = iconClick) {
                Icon(Icons.Rounded.Menu, contentDescription = "Menu")
            }
        }
    )
}