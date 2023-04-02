package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.api.NewsApi
import com.example.news.model.News
import com.example.news.ui.theme.NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                Scaffold(topBar = { TopAppBar(title = { Text("Лента новостей КубГАУ") }) }) { paddingValues ->

                    val newsListState = remember {
                        mutableStateOf<List<News>>(emptyList())
                    }

                    LaunchedEffect(key1 = Unit) {
                        val newsList =
                            NewsApi.newsApiService.getNews("6df2f5d38d4e16b5a923a6d4873e2ee295d0ac90")
                        newsListState.value = newsList
                    }
                    NewsList(news = newsListState.value, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}