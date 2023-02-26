package com.example.firstlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.firstlab.ui.theme.FirstLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var incrementValue by remember {
                mutableStateOf(0)
            }
            FirstLabTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Инкремент")
                            }
                        )
                    }) { paddingValues ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        Text(text = "Значение инкремента")
                        Text(incrementValue.toString(), fontSize = 32.sp)
                        Row {
                            Button(
                                onClick = {
                                    incrementValue--
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "minus"
                                )
                            }
                            Button(
                                onClick = {
                                    incrementValue++
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                            ) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                            }
                        }
                        TextButton(
                            onClick = {
                                incrementValue = 0
                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
                        ) {
                            Text(
                                text = "Сбросить"
                            )
                        }
                    }
                }
            }
        }
    }
}