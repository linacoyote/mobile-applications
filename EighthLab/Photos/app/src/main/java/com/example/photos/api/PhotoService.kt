package com.example.photos.api

import com.example.photos.model.Photo
import retrofit2.http.GET

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(): List<Photo>
}
