package com.example.fourthlab.entity

data class Dormitory(
    val name: String,
    val imageId: Int,
    val address: String,
    val phone: String,
    val description: String,
    val likes: Int,
    val liked: Boolean
)
