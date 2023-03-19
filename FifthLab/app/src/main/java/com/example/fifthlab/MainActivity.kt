package com.example.fifthlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fifthlab.ui.SimpleList
import com.example.fifthlab.ui.StringNumberList
import com.example.fifthlab.ui.TwoPowerList
import com.example.fifthlab.ui.theme.FifthLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FifthLabTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Список элементов")
                            }
                        )
                    }
                ) {
                    var listType by remember { mutableStateOf(1) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Button(onClick = { listType = 1 }) {
                                Text(text = "1")
                            }
                            Button(onClick = { listType = 2 }) {
                                Text(text = "2")
                            }
                            Button(onClick = { listType = 3 }) {
                                Text(text = "3")
                            }
                        }
                        when (listType) {
                            1 -> {
                                SimpleList()
                            }
                            2 -> {
                                StringNumberList()
                            }
                            3 -> {
                                TwoPowerList()
                            }
                        }
                    }
                }
            }
        }
    }
}
