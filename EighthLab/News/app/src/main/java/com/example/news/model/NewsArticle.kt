package com.example.news.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("ID")
    val id: String,
    @SerializedName("ACTIVE_FROM")
    val activeFrom: String,
    @SerializedName("TITLE")
    val title: String,
    @SerializedName("PREVIEW_TEXT")
    val previewText: String,
    @SerializedName("PREVIEW_PICTURE_SRC")
    val previewPictureSrc: String,
    @SerializedName("DETAIL_PAGE_URL")
    val detailPageUrl: String,
    @SerializedName("DETAIL_TEXT")
    val detailText: String,
    @SerializedName("LAST_MODIFIED")
    val lastModified: String
)