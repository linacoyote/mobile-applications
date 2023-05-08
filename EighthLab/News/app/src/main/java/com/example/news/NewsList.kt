package com.example.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.parseAsHtml
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.news.api.NewsApi
import com.example.news.model.News

@Composable
fun NewsList(news: List<News>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(news) { news ->
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                val loader = ImageLoader.Builder(LocalContext.current)
                    .okHttpClient(NewsApi.httpClient)
                    .build()

                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        rememberAsyncImagePainter(
                            model = news.previewPictureSrc,
                            imageLoader = loader
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(text = news.lastModified, style = MaterialTheme.typography.caption)
                    Text(
                        text = news.title,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = news.previewText.parseAsHtml().toString(),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                }
            }
        }
    }
}
