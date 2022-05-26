package com.jakubveverka.spacelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.model.Launch
import com.jakubveverka.spacelist.list.viewModel.LaunchListViewModel
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.spacenavigation.Screen
import java.text.SimpleDateFormat

@Composable
fun LaunchList(launchListViewModel: LaunchListViewModel, navigationManager: NavigationManager) {
    val shouldOpenDialog = rememberSaveable { mutableStateOf(true) }
    launchListViewModel.launches.collectAsState().also {
        when (val result = it.value) {
            is ApiResult.Error -> {
                LaunchListColumn(result.data, navigationManager)
                if (shouldOpenDialog.value) {
                    AlertDialog(
                        onDismissRequest = { shouldOpenDialog.value = false },
                        title = { Text(text = "Error = ${result.message}") },
                        buttons = { }
                    )
                }
            }
            is ApiResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LaunchListColumn(result.data, navigationManager)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is ApiResult.Success -> {
                LaunchListColumn(result.data, navigationManager)
            }
        }
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
                    .clickable { navigationManager.navigate(Screen.LaunchDetail, listOf(Pair(Screen.Parameter.LAUNCH_ID, launch.id))) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (launch.success) Color.Magenta else Color.Red)
                        .padding(5.dp)
                ) {
                    Column(modifier = Modifier.weight(.3f)) {
                        Text(text = launch.name)
                    }
                    Column(modifier = Modifier.weight(.3f)) {
                        val dateText = launch.date?.let {
                            SimpleDateFormat.getDateTimeInstance().format(it * 1000)
                        } ?: "No date available"
                        Text(text = "Date $dateText")
                    }
                    Column(modifier = Modifier.weight(.3f)) {
                        Text(text = "Flight number ${launch.flightNumber}")
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
                        .background(Color.Blue)
                        .padding(5.dp)
                ) {
                    Text(
                        text = launch.details?.take(100)
                            ?.run { if (length == 100) "$this..." else this } ?: "No details")
                }
            }
        }
    }
}