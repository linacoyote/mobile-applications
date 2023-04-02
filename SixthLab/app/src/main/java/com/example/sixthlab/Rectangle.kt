package com.example.sixthlab

data class Rectangle(
    val width: Int?,
    val height: Int?,
) {
    fun area(): Int? {
        if (width == null || height == null) {
            return null
        }
        return width * height
    }
}
