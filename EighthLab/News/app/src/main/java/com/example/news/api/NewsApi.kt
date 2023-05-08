package com.example.news.api

import android.annotation.SuppressLint
import android.content.Context
import coil.ImageLoader
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }
})

private val sslContext = SSLContext.getInstance("SSL").apply {
    init(null, trustAllCerts, java.security.SecureRandom())
}


object NewsApi {
    private const val baseUrl = "https://kubsau.ru/api/"
    val httpClient = OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, TrustAllCerts())
        .hostnameVerifier { _, _ -> true }
        .build()

    class TrustAllCerts : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) =
            Unit

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) =
            Unit

        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsApiService: NewsApiService = retrofit.create(NewsApiService::class.java)
}