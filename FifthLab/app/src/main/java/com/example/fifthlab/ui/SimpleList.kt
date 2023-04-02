package com.example.fifthlab.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleList() {
    val items = List(3) {
        it
    }
    LazyColumn() {
        items(items = items) { item ->
            Column {
                Text(item.toString(), modifier = Modifier.padding(16.dp))
                Divider()
            }
        }
    }
}