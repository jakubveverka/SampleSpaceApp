package com.jakubveverka.samplespaceapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jakubveverka.spacedata.api.ApiResult
import com.jakubveverka.spacelaunch.detail.LaunchDetail
import com.jakubveverka.spacelaunch.detail.viewModel.LaunchDetailViewModel
import com.jakubveverka.spacelaunch.list.LaunchList
import com.jakubveverka.spacelaunch.list.viewModel.LaunchListViewModel
import com.jakubveverka.spacelaunch.list.viewModel.filterByName
import com.jakubveverka.spacenavigation.Screen

@Composable
fun Navigation(
    navController: NavHostController,
    navigationManager: com.jakubveverka.spacenavigation.NavigationManager,
    launchListViewModel: LaunchListViewModel,
    launchDetailViewModel: LaunchDetailViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.LaunchList.route
    ) {
        composable(route = Screen.LaunchList.route) {
            val shouldOpenDialog = rememberSaveable { mutableStateOf(true) }
            val filterName = rememberSaveable { mutableStateOf("") }
            val launchList = launchListViewModel.launches
                .filterByName(filterName.value)
                .collectAsState(ApiResult.Loading(emptyList()))
            LaunchList(
                launchList.value,
                navigationManager,
                filterName.value,
                shouldOpenDialog.value,
                { filterName.value = it },
                { shouldOpenDialog.value = false }
            )
        }
        composable(route = Screen.Screen2.route) {
            Text(text = "Screen 2")
        }
        composable(route = Screen.Screen3.route) {
            Text(text = "Screen 3")
        }
        composable(
            route = Screen.LaunchDetail.route,
            arguments = Screen.LaunchDetail.getNavArguments()
        ) { backStackEntry ->
            val launchId = backStackEntry.arguments?.getString(Screen.Parameter.LAUNCH_ID.paramName)
                ?: throw java.lang.IllegalStateException("No 'launchId' parameter passed to LaunchDetail route")
            val launch by launchDetailViewModel.getLaunch(launchId).collectAsState(null)
            LaunchDetail(launch)
        }
    }
}

private fun Screen.getNavArguments() =
    parameters.map { param ->
        navArgument(param.paramName) {
            type = when (param.type) {
                String::class.java -> NavType.StringType
                else -> throw IllegalStateException("Unknown param type.")
            }
            if (param.defaultValue != null) {
                defaultValue = param.defaultValue
            }
        }
    }
