package com.jakubveverka.spacelaunch.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacelaunch.ui.LaunchDetails
import com.jakubveverka.spacelaunch.ui.LaunchFail
import com.jakubveverka.spacelaunch.ui.LaunchSuccess
import com.jakubveverka.spacelaunch.ui.WhiteText
import com.jakubveverka.spacelaunch.uimodel.LaunchUi
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.Screen

@Composable
fun LaunchList(
    launchListResult: ApiResult<List<LaunchUi>>,
    navigationManager: NavigationManager,
    filterName: String,
    shouldOpenDialog: Boolean,
    filterValueChanged: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        FilterTextField(filterName, filterValueChanged)
        when (launchListResult) {
            is ApiResult.Error -> {
                LaunchListColumn(launchListResult.data, navigationManager)
                HandleDialog(shouldOpenDialog, launchListResult.message, onDismiss)
            }
            is ApiResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LaunchListColumn(launchListResult.data, navigationManager)
                    Loading()
                }
            }
            is ApiResult.Success -> {
                LaunchListColumn(launchListResult.data, navigationManager)
            }
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FilterTextField(
    filterName: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = filterName,
        onValueChange = { onValueChanged(it) },
        placeholder = { Text(text = "Filter launches by name...") },
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
private fun HandleDialog(
    shouldOpenDialog: Boolean,
    dialogTitle: String?,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (shouldOpenDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Error = $dialogTitle") },
            buttons = { },
            modifier = modifier
        )
    }
}

@Composable
private fun LaunchListColumn(
    data: List<LaunchUi>,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(data) { _, launch ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(5.dp)
                    .clickable {
                        navigationManager.navigate(
                            Screen.LaunchDetail,
                            listOf(Pair(Screen.Parameter.LAUNCH_ID, launch.id))
                        )
                    }
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (launch.success) LaunchSuccess else LaunchFail)
                        .padding(5.dp)
                ) {
                    val columnModifier = Modifier
                        .weight(.3f)
                        .padding(3.dp)
                    Column(modifier = columnModifier) {
                        WhiteText(text = launch.name)
                    }
                    Column(modifier = columnModifier) {
                        val dateText = launch.date ?: "No date available"
                        WhiteText(text = "Date $dateText")
                    }
                    Column(modifier = columnModifier) {
                        WhiteText(text = "Flight number ${launch.flightNumber}")
                    }
                }
                Spacer(
                    modifier = Modifier
                        .size(2.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LaunchDetails)
                        .padding(5.dp)
                ) {
                    WhiteText(
                        text = launch.details?.take(100)
                            ?.run { if (length == 100) "$this..." else this } ?: "No details"
                    )
                }
            }
        }
    }
}