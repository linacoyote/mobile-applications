package com.example.photos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.photos.api.Api
import com.example.photos.model.Photo
import com.example.photos.ui.screen.PhotoList
import com.example.photos.ui.theme.EightsLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EightsLabTheme {
                var photos by remember { mutableStateOf(emptyList<Photo>()) }

                LaunchedEffect(Unit) {
                    photos = Api.photoService.getPhotos()
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Фотогалерея") }
                        )
                    }
                ) {padding ->
                    if (photos.isNotEmpty()) {
                        PhotoList(photos = photos, modifier = Modifier.padding(padding))
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
