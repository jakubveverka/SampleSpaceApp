package com.jakubveverka.spacelist.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LaunchDetail(launchId: String) {
    Text(text = "Hello Im Detail, launchId = $launchId")
}