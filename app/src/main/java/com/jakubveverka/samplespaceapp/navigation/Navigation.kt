package com.jakubveverka.samplespaceapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jakubveverka.spacelist.LaunchList
import com.jakubveverka.spacelist.viewModel.LaunchListViewModel

@Composable
fun Navigation(navController: NavHostController, launchListViewModel: LaunchListViewModel) {
    NavHost(navController = navController, startDestination = Screen.Screen1.route) {
        composable(route = Screen.Screen1.route) {
            LaunchList(launchListViewModel)
        }
        composable(route = Screen.Screen2.route) {
            Text(text = "Hello how are you?")
        }
        composable(route = Screen.Screen3.route) {
            Text(text = "Hello Im good")
        }
    }
}