package com.jakubveverka.samplespaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jakubveverka.samplespaceapp.menu.MenuItem
import com.jakubveverka.samplespaceapp.navigation.Navigation
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.samplespaceapp.ui.composable.Drawer
import com.jakubveverka.samplespaceapp.ui.composable.MyTopBar
import com.jakubveverka.samplespaceapp.ui.theme.SampleSpaceAppTheme
import com.jakubveverka.spacelaunch.detail.viewModel.LaunchDetailViewModel
import com.jakubveverka.spacelaunch.list.viewModel.LaunchListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()

    private val launchListViewModel by viewModel<LaunchListViewModel>()
    private val launchDetailViewModel by viewModel<LaunchDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSpaceAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                val scope = rememberCoroutineScope()

                HandleNavigation(navController, scope) { scaffoldState.drawerState.close() }

                Content(navController, scaffoldState, scope)
            }
        }
    }

    @Composable
    private fun HandleNavigation(
        navController: NavHostController,
        scope: CoroutineScope,
        closeDrawer: suspend () -> Unit
    ) {
        navigationManager.destinationState.collectAsState().value.also { navDestination ->
            if (navDestination != null) {
                scope.launch {
                    val paramsToReplace = navDestination.params.map {
                        Pair(
                            "{${it.first.paramName}}",
                            it.second
                        )
                    }

                    val finalRoute = navDestination.screen.route.replace(
                        paramsToReplace
                    )

                    if (finalRoute != navigationManager.currentRoute) {
                        closeDrawer()
                        navigationManager.currentRoute = finalRoute
                        navController.navigate(
                            navDestination.screen.route.replace(
                                paramsToReplace
                            )
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun Content(
        navController: NavHostController,
        scaffoldState: ScaffoldState,
        scope: CoroutineScope
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { MyTopBar(navigationManager) { scope.launch { scaffoldState.drawerState.open() } } },
            drawerBackgroundColor = MaterialTheme.colors.background,
            drawerContent = { Drawer(MenuItem.values().toList(), navigationManager) }
        ) {
            Navigation(
                navController,
                navigationManager,
                launchListViewModel,
                launchDetailViewModel
            )
        }
    }

    private fun String.replace(replacements: List<Pair<String, String>>): String {
        var result = this
        replacements.forEach { (toReplace, newValue) ->
            result = result.replace(toReplace, newValue)
        }
        return result
    }

}