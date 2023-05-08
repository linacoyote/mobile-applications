package com.example.coffemachine.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Machine {

    private var _resources = MutableStateFlow(Resources(0, 0, 0, 0))
    val resources = _resources.asStateFlow()

    private var _coffeeStages = MutableStateFlow<CoffeeStates>(CoffeeStates.Nothing)
    val coffeeStages = _coffeeStages.asStateFlow()

    fun fillResources(resources: Resources) {
        this._resources.update {
            resources
        }
    }

    fun putMoney(money: Int) {
        this._resources.update {
            val cash = it.cash + money
            it.copy(cash = if (cash > 0) cash else 0)
        }
    }
    fun putMilk(milk: Int) {
        this._resources.update {
            val newMilk = it.milk + milk
            it.copy(milk = if (newMilk > 0) newMilk else 0)
        }
    }

    fun putBeans(beans: Int) {
        this._resources.update {
            val newBeans = it.coffeeBeans + beans
            it.copy(coffeeBeans = if (newBeans > 0) newBeans else 0)
        }
    }

    fun putWater(water: Int) {
        this._resources.update {
            val newWater = it.water + water
            it.copy(water = if (newWater > 0) newWater else 0)
        }
    }

    fun moneyOff(money: Int) {
        this._resources.update {
            val cash = it.cash - money
            it.copy(cash = if (cash > 0) cash else 0)
        }
    }

    suspend fun makeCoffeByType(coffee: Coffee) {
        if (_coffeeStages.value != CoffeeStates.Nothing) {
            _coffeeStages.update {
                it.apply { message = "Машина занята: ${it.message}" }
            }
            return
        }
        if (isAvailableResource(coffee)) {
            makeCoffee(coffee)
        } else {
            _coffeeStages.update {
                CoffeeStates.Error("Недостаточно ресурсов. Требуется:\n${coffee.printResources()}\nИмеется: ${resources.value.printResources()}")
            }
        }
    }

    private fun isAvailableResource(coffee: Coffee): Boolean {
        return coffee.coffeeBeans() <= resources.value.coffeeBeans
                && coffee.water() <= resources.value.water
                && coffee.milk() <= resources.value.milk
                && coffee.cash() <= resources.value.cash
    }

    private suspend fun makeCoffee(coffee: Coffee) {
        _resources.update {
            it.copy(
                coffeeBeans = it.coffeeBeans - coffee.coffeeBeans(),
                milk = it.milk - coffee.milk(),
                water = it.water - coffee.water(),
                cash = it.cash - coffee.cash()
            )
        }
        heatWater()
        heatCoffee()
        shakeMilk()
        mixCoffeeAndMilk()
        coffeeIsReady()
    }

    private suspend fun heatWater() {
        _coffeeStages.update {
            CoffeeStates.HeatWater
        }
        delay(_coffeeStages.value.duration)
    }

    private suspend fun heatCoffee() {
        _coffeeStages.update {
            CoffeeStates.HeatCoffee
        }
        delay(_coffeeStages.value.duration)
    }

    private suspend fun shakeMilk() {
        _coffeeStages.update {
            CoffeeStates.ShakeMilk
        }
        delay(_coffeeStages.value.duration)
    }

    private suspend fun mixCoffeeAndMilk() {
        _coffeeStages.update {
            CoffeeStates.MixCoffeeAndMilk
        }
        delay(_coffeeStages.value.duration)
    }
    private suspend fun coffeeIsReady() {
        _coffeeStages.update {
            CoffeeStates.Ready
        }
        delay(2000)
        _coffeeStages.update {
            CoffeeStates.Nothing
        }
    }
}