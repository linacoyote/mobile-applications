package com.example.coffemachine.model

enum class Coffee : ICoffee {
    Americano {
        override fun coffeeBeans() = 100
        override fun milk() = 50

        override fun water() = 150

        override fun cash() = 299
    },

    Espresso {
        override fun coffeeBeans() = 50

        override fun milk() = 0

        override fun water() = 100

        override fun cash() = 150
    }

}