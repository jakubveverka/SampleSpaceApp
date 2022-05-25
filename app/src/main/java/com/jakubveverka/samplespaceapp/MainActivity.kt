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
import com.jakubveverka.samplespaceapp.navigation.NavigationManager
import com.jakubveverka.samplespaceapp.ui.composable.Drawer
import com.jakubveverka.samplespaceapp.ui.composable.MyTopBar
import com.jakubveverka.samplespaceapp.ui.theme.SampleSpaceAppTheme
import com.jakubveverka.spacelist.viewModel.LaunchListViewModel
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

                navigationManager.destinationState.collectAsState().value.also { screen ->
                    if (screen != null) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navController.navigate(screen.route)
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { MyTopBar(scaffoldState, scope) },
                    drawerBackgroundColor = MaterialTheme.colors.background,
                    drawerContent = { Drawer(MenuItem.values().toList(), navigationManager) }
                ) {
                    Navigation(navController, launchListViewModel)
                }
            }
        }
    }

}