package com.example.coffemachine.model

sealed class CoffeeStates(var message: String, val duration: Long) {
    object Nothing : CoffeeStates("", 0)
    object HeatWater : CoffeeStates("Подогрев воды", 3000)
    object HeatCoffee : CoffeeStates("Заваривание кофе", 5000)
    object ShakeMilk : CoffeeStates("Взбивание молока", 5000)
    object MixCoffeeAndMilk : CoffeeStates("Смешивание молока и кофе", 3000)
    object Ready : CoffeeStates("Кофе готово!", 0)
    class Error(message: String) : CoffeeStates(message, 0)
}