package com.jakubveverka.samplespaceapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jakubveverka.spacelist.LaunchList
import com.jakubveverka.spacelist.detail.LaunchDetail
import com.jakubveverka.spacelist.detail.viewModel.LaunchDetailViewModel
import com.jakubveverka.spacelist.list.viewModel.LaunchListViewModel
import com.jakubveverka.spacenavigation.Screen
import kotlinx.coroutines.CoroutineScope

@Composable
fun Navigation(
    navController: NavHostController,
    navigationManager: com.jakubveverka.spacenavigation.NavigationManager,
    launchListViewModel: LaunchListViewModel,
    launchDetailViewModel: LaunchDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LaunchList.route
    ) {
        composable(route = Screen.LaunchList.route) {
            LaunchList(launchListViewModel, navigationManager)
        }
        composable(route = Screen.Screen2.route) {
            Text(text = "Screen 2")
        }
        composable(route = Screen.Screen3.route) {
            Text(text = "Screen 3")
        }
        composable(
            route = Screen.LaunchDetail.route,
            arguments = getNavArgumentsForScreen(Screen.LaunchDetail)
        ) { backStackEntry ->
            LaunchDetail(
                launchDetailViewModel,
                backStackEntry.arguments?.getString(Screen.Parameter.LAUNCH_ID.paramName)
                    ?: throw java.lang.IllegalStateException("No 'launchId' parameter passed to LaunchDetail route")
            )
        }
    }
}

private fun getNavArgumentsForScreen(screen: Screen) =
    screen.parameters.map { param ->
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