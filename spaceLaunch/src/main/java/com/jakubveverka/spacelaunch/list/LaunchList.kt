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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.model.Launch
import com.jakubveverka.spacelaunch.list.viewModel.LaunchListViewModel
import com.jakubveverka.spacelaunch.ui.LaunchDetails
import com.jakubveverka.spacelaunch.ui.LaunchFail
import com.jakubveverka.spacelaunch.ui.LaunchSuccess
import com.jakubveverka.spacelaunch.ui.WhiteText
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.Screen
import java.text.SimpleDateFormat

@Composable
fun LaunchList(launchListViewModel: LaunchListViewModel, navigationManager: NavigationManager) {
    val shouldOpenDialog = rememberSaveable { mutableStateOf(true) }
    val filterName = rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        FilterTextField(filterName)
        launchListViewModel.launches.collectAsState().also {
            val filteredData = it.value.data.filterByName(filterName.value)
            when (it.value) {
                is ApiResult.Error -> {
                    LaunchListColumn(filteredData, navigationManager)
                    HandleDialog(shouldOpenDialog, it.value.message)
                }
                is ApiResult.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LaunchListColumn(filteredData, navigationManager)
                        Loading()
                    }
                }
                is ApiResult.Success -> {
                    LaunchListColumn(filteredData, navigationManager)
                }
            }
        }
    }
}

private fun List<Launch>.filterByName(filterName: String): List<Launch> {
    return filter { launch ->
        launch.name.contains(filterName, ignoreCase = true)
    }
}

@Composable
private fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(100.dp).background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FilterTextField(filterName: MutableState<String>) {
    OutlinedTextField(
        value = filterName.value,
        onValueChange = { filterName.value = it },
        placeholder = { Text(text = "Filter launches by name...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
private fun HandleDialog(shouldOpenDialog: MutableState<Boolean>, dialogTitle: String?) {
    if (shouldOpenDialog.value) {
        AlertDialog(
            onDismissRequest = { shouldOpenDialog.value = false },
            title = { Text(text = "Error = $dialogTitle") },
            buttons = { }
        )
    }
}

@Composable
private fun LaunchListColumn(data: List<Launch>, navigationManager: NavigationManager) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                        val dateText = launch.date?.let {
                            SimpleDateFormat.getDateTimeInstance().format(it * 1000)
                        } ?: "No date available"
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