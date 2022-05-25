package com.jakubveverka.samplespaceapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Screen1.route) {
        composable(route = Screen.Screen1.route) {
            Text(text = "Hello World")
        }
        composable(route = Screen.Screen2.route) {
            Text(text = "Hello how are you?")
        }
        composable(route = Screen.Screen3.route) {
            Text(text = "Hello Im good")
        }
    }
}