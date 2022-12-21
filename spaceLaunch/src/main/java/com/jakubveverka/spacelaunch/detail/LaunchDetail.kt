package com.jakubveverka.spacelaunch.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacelaunch.ui.LaunchFail
import com.jakubveverka.spacelaunch.ui.LaunchSuccess
import com.jakubveverka.spacelaunch.ui.WhiteText
import com.jakubveverka.spacelaunch.uimodel.LaunchUi

@Composable
fun LaunchDetail(launch: LaunchUi?) {
    if (launch == null) return

    val dateText = launch.date ?: "No date available"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (launch.success) LaunchSuccess else LaunchFail)
            .padding(10.dp)
    ) {
        WhiteText(text = "Name: ${launch.name}")
        WhiteText(text = "Date: $dateText")
        WhiteText(text = "Flight Number: ${launch.flightNumber}")
        WhiteText(text = "State: ${if (launch.success) "Successful" else "Failed"}")
        WhiteText(text = "Details: ${launch.details ?: "No details"}")
    }
}