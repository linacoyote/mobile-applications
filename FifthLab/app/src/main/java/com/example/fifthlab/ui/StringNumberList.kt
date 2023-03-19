package com.example.fifthlab.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun StringNumberList() {
    val list = remember { mutableStateListOf<Int>(10) }
    val lazyListState = rememberLazyListState()
    if (lazyListState.firstVisibleItemIndex >= list.size - 100 ) {
        list.addAll(List(100) { it })
    }
    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
        itemsIndexed(list) { index, item ->
            Text("строка $index")
        }
    }
}