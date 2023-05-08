package com.example.coffemachine.model

data class Resources(
    val coffeeBeans: Int,
    val milk: Int,
    val water: Int,
    val cash: Int
) {
    fun printResources(): String {
        return "Зерна: ${coffeeBeans}\nМолоко: ${milk}\nВода: ${water}\nСтоимость: ${cash}"
    }
}
