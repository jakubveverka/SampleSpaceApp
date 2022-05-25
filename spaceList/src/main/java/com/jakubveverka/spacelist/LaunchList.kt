package com.jakubveverka.spacelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacedata.model.Launch
import com.jakubveverka.spacelist.viewModel.LaunchListViewModel

@Composable
fun LaunchList(launchListViewModel: LaunchListViewModel) {
    launchListViewModel.launches.collectAsState().also {
        when (val result = it.value) {
            is ApiResult.Error -> {
                LaunchListColumn(result.data)
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(text = "Error = ${result.message}") },
                    buttons = { }
                )
            }
            is ApiResult.Loading -> {
                Box {
                    LaunchListColumn(result.data)
                    CircularProgressIndicator()
                }
            }
            is ApiResult.Success -> {
                LaunchListColumn(result.data)
            }
        }
    }
}

@Composable
private fun LaunchListColumn(data: List<Launch>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(data) { _, launch ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .padding(5.dp)
                ) {
                    Column(modifier = Modifier.weight(.3f)) {
                        Text(text = launch.name)
                    }
                    Column(modifier = Modifier.weight(.3f)) {
                        Text(text = "Date ${launch.date}") //TODO format
                    }
                    Column(modifier = Modifier.weight(.3f)) {
                        Text(text = "Flight number ${launch.flightNumber}")
                    }
                }
                Spacer(modifier = Modifier
                    .size(2.dp)
                    .fillMaxWidth())
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