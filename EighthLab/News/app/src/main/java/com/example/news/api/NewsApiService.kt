package com.example.news.api

import com.example.news.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("getNews.php")
    suspend fun getNews(@Query("key") key: String): List<News>
}