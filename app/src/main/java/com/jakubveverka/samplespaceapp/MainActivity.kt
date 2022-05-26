package com.jakubveverka.samplespaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.jakubveverka.samplespaceapp.menu.MenuItem
import com.jakubveverka.samplespaceapp.navigation.Navigation
import com.jakubveverka.spacenavigation.NavigationManager
import com.jakubveverka.samplespaceapp.ui.composable.Drawer
import com.jakubveverka.samplespaceapp.ui.composable.MyTopBar
import com.jakubveverka.samplespaceapp.ui.theme.SampleSpaceAppTheme
import com.jakubveverka.spacelist.list.viewModel.LaunchListViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()

    private val launchListViewModel by viewModel<LaunchListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSpaceAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                val scope = rememberCoroutineScope()

                navigationManager.destinationState.collectAsState().value.also { navDestination ->
                    if (navDestination != null) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            val paramsToReplace = navDestination.params.map { Pair("{${it.first.paramName}}", it.second) }

                            navController.navigate(navDestination.screen.route.replace(paramsToReplace))
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { MyTopBar(scaffoldState, scope) },
                    drawerBackgroundColor = MaterialTheme.colors.background,
                    drawerContent = { Drawer(MenuItem.values().toList(), navigationManager) }
                ) {
                    Navigation(navController, navigationManager, launchListViewModel)
                }
            }
        }
    }

    private fun String.replace(replacements: List<Pair<String, String>>): String {
        var result = this
        replacements.forEach { (toReplace, newValue) -> result = result.replace(toReplace, newValue) }
        return result
    }

}