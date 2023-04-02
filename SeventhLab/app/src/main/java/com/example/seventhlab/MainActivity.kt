package com.example.seventhlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seventhlab.ui.theme.SeventhLabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeventhLabTheme {
                val navHostController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = { state ->
                        SnackbarHost(state)
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navHostController,
                        modifier = Modifier.padding(paddingValues),
                        startDestination = "start"
                    ) {
                        composable("start") {
                            StartScreen(
                                onStartChoice = {
                                    navHostController.navigate("choice")
                                }
                            )
                        }
                        composable("choice") {
                            ChoiceScreen(
                                onNavigateUp = navHostController::navigateUp,
                                onChoiceChoose = { choice ->
                                    navHostController.navigateUp()
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(if (choice) "Да" else "Нет")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}