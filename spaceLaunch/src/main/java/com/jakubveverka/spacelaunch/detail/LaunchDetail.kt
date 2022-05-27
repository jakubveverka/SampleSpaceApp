package com.jakubveverka.spacelaunch.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacelaunch.detail.viewModel.LaunchDetailViewModel
import java.text.SimpleDateFormat

@Composable
fun LaunchDetail(
    launchDetailViewModel: LaunchDetailViewModel,
    launchId: String
) {

    launchDetailViewModel.getLaunch(launchId).collectAsState(initial = null).also { launchState ->
        val launch = launchState.value ?: return@also
        val dateText = launch.date?.let {
            SimpleDateFormat.getDateTimeInstance().format(it * 1000)
        } ?: "No date available"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (launch.success) Color.Magenta else Color.Red)
                .padding(10.dp)
        ) {
            Text(text = "Name: ${launch.name}")
            Text(text = "Date: $dateText")
            Text(text = "Flight Number: ${launch.flightNumber}")
            Text(text = "State: ${if (launch.success) "Successful" else "Failed"}")
            Text(text = "Details: ${launch.details ?: "No details"}")
        }
    }
}