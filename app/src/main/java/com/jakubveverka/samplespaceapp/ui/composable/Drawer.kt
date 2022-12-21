package com.jakubveverka.samplespaceapp.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.samplespaceapp.menu.MenuItem
import com.jakubveverka.spacenavigation.NavigationManager

@Composable
fun Drawer(
    menuItems: List<MenuItem>,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(menuItems.size) { index ->
            DrawerRow(menuItems[index], index == (menuItems.size - 1), navigationManager)
        }
    }
}


@Composable
private fun DrawerRow(
    menuItem: MenuItem,
    isLast: Boolean,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable { navigationManager.navigate(menuItem.screen) }
            .padding(10.dp)
    ) {
        Text(text = menuItem.title, style = MaterialTheme.typography.h6)
    }
    if (!isLast) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .height(1.dp)
        )
    }
}