package com.example.coffemachine.model

interface ICoffee {
    fun coffeeBeans(): Int
    fun milk(): Int
    fun water(): Int
    fun cash(): Int

    fun printResources(): String {
        return "Зерно: ${coffeeBeans()}\nМолоко: ${milk()}\nВода: ${water()}\nСтоимость: ${cash()}"
    }
}