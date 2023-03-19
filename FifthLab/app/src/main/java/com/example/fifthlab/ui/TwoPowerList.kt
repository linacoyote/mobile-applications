package com.example.fifthlab.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun TwoPowerList() {
    val list = remember { mutableStateListOf<Int>(10) }
    val lazyListState = rememberLazyListState()
    if (lazyListState.firstVisibleItemIndex >= list.size - 40 ) {
        list.addAll(List(30) { it })
    }
    LazyColumn(state = lazyListState) {
        itemsIndexed(list) { index, item ->
            Text(
                text = "2 ^ $index = ${2.toDouble().pow(index.toDouble()).toLong()}",
                modifier = Modifier.padding(16.dp)
            )
            Divider()
        }
    }
}