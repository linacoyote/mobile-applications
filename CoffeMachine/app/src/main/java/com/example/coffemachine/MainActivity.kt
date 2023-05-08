package com.example.coffemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.CoffeeMaker
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffemachine.model.CoffeeStates
import com.example.coffemachine.model.Machine
import com.example.coffemachine.model.Resources
import com.example.coffemachine.ui.screen.CoffeeScreen
import com.example.coffemachine.ui.screen.ResourcesScreen
import com.example.coffemachine.ui.theme.CoffeMachineTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeMachineTheme {
                val machine = remember { Machine() } // Создание машины
                machine.fillResources(Resources(1000, 1000, 1000, 1000))
                val scope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(Unit) {
                    machine.coffeeStages.collect {// Отслеживание состояния
                        if (it !is CoffeeStates.Nothing) {
                            scaffoldState.snackbarHostState.showSnackbar( // Показ снэкбара с сообщением
                                it.message,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = { state ->
                        SnackbarHost(hostState = state)
                    },
                    bottomBar = {
                        BottomNavigation {
                            BottomNavigationItem(
                                selected = navController.currentDestination?.route == "coffee",
                                onClick = {
                                    navController.navigate("coffee") {
                                        this.launchSingleTop = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.CoffeeMaker, null
                                    )
                                }
                            )
                            BottomNavigationItem(
                                selected = navController.currentDestination?.route == "resources",
                                onClick = {
                                    navController.navigate("resources") {
                                        this.launchSingleTop = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Coffee,
                                        null
                                    )
                                }
                            )
                        }
                    },
                    topBar = {
                        TopAppBar(
                            title = { Text("Coffee Machine") }
                        )
                    }
                ) { paddingValues ->
                    val resources by machine.resources.collectAsState()
                    NavHost(
                        navController = navController,
                        startDestination = "coffee",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("coffee") {
                            CoffeeScreen(
                                resources = resources,
                                onMakeCoffeeClicked = {
                                    scope.launch {
                                        machine.makeCoffeByType(it)
                                    }
                                },
                                onPutMoney = machine::putMoney,
                                onMoneyOff = machine::moneyOff,
                            )
                        }
                        composable("resources") {
                            ResourcesScreen(
                                resources = resources,
                                onPutMilk = machine::putMilk,
                                onPutWater = machine::putWater,
                                onPutBeans = machine::putBeans,
                                onPutCash = machine::putMoney
                            )
                        }
                    }
                }
            }
        }
    }
}