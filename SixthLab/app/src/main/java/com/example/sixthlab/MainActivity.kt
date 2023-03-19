package com.example.sixthlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sixthlab.ui.theme.SixthLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SixthLabTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Калькулятор площади")
                            }
                        )
                    }
                ) {
                    var rectangle by remember { mutableStateOf(Rectangle(null, null)) }
                    var showError by remember { mutableStateOf(false) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Ширина (мм):", modifier = Modifier.width(120.dp))
                            OutlinedTextField(
                                value = rectangle.width?.toString() ?: "",
                                onValueChange = { value ->
                                    showError = false
                                    rectangle = rectangle.copy(width = value.toIntOrNull())
                                },
                                label = {
                                    Text(text = "Задайте ширину")
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                isError = showError
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Высота (мм):", modifier = Modifier.width(120.dp))
                            OutlinedTextField(
                                value = rectangle.height?.toString() ?: "",
                                onValueChange = { value ->
                                    showError = false
                                    rectangle = rectangle.copy(height = value.toIntOrNull())
                                },
                                label = {
                                    Text(text = "Задайте высоту")
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                isError = showError
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        var answerText by remember { mutableStateOf("Задайте параметры") }
                        Button(
                            onClick = {
                                if (rectangle.width == null || rectangle.height == null) {
                                    showError = true
                                    answerText = "Задайте параметры"
                                } else {
                                    answerText =
                                        "S = ${rectangle.width} * ${rectangle.height} = ${rectangle.area()} (мм2)"
                                }
                            }
                        ) {
                            Text(text = "Вычислить")
                        }
                        Text(answerText, style = MaterialTheme.typography.h5)
                    }
                }
            }
        }
    }
}

