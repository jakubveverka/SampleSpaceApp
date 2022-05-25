package com.jakubveverka.samplespaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jakubveverka.samplespaceapp.menu.MenuItem
import com.jakubveverka.samplespaceapp.navigation.Navigation
import com.jakubveverka.samplespaceapp.navigation.NavigationManager
import com.jakubveverka.samplespaceapp.ui.composable.Drawer
import com.jakubveverka.samplespaceapp.ui.composable.MyTopBar
import com.jakubveverka.samplespaceapp.ui.theme.SampleSpaceAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigationManager: NavigationManager by inject()

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
                    Navigation(navController)
                }
            }
        }
    }

}