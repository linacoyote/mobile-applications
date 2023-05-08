# Кофе-машина

Это приложение имитирует работу кофе-машины, которая может приготовить два вида кофе: Americano и
Espresso.

## Реализация

В пакете `model` реализованы несколько классов и интерфейсов.

### Класс `Machine`

Этот класс представляет кофе-машину. Он содержит ресурсы, доступные на данный момент,
а также методы для добавления и снятия ресурсов и денег, ответственен за смену состояния
кофе машины.

### Интерфейс `ICoffee`

Этот интерфейс определяет методы для получения количества ресурсов, необходимых для приготовления
одной порции кофе.

### Перечисление `Coffee`

Это перечисление содержит два вида кофе: Americano и Espresso. Они реализуют
интерфейс ICoffee и определяют количество ресурсов, необходимых для приготовления одной порции.

```kotlin
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
```

### Класс CoffeeStates

`CoffeeStates` - это класс, представляющий состояние кофе-машины в процессе приготовления кофе.
Он определяет различные стадии приготовления кофе и длительность каждой стадии.
Класс `CoffeeStates` реализован в виде плотно связанной иерархии классов, используя
объекты-одиночки и вложенные классы.

## Использование

Для использования кофе-машины необходимо создать экземпляр класса `Machine`,
заполнить необходимые ресурсы (зерна, молоко, воду, деньги), а затем вызвать метод
`makeCoffeeByType` с одним из элементов перечисления `Coffee`.
Если ресурсов недостаточно, будет выведено сообщение об ошибке. Если кофе-машина занята
приготовлением другого кофе, будет выведено сообщение о занятости.

```kotlin
val machine = remember { Machine() } // Создание машины
machine.fillResources(Resources(1000, 1000, 1000, 1000))
val scope = rememberCoroutineScope()
val scaffoldState = rememberScaffoldState()
LaunchedEffect(Unit) {
    machine.coffeeStages.collect {// Отслеживание состояния 
        if (it !is CoffeeStates.Nothing) {
            scaffoldState.snackbarHostState.showSnackbar( // Показ снэкбара с сообщением
                it.message,
                duration = SnackbarDuration.Short
            )
        }
    }
}
NavHost(
    navController = navController,
    startDestination = "coffee"
) {
    composable("coffee") {
        CoffeeScreen(
            resources = resources,
            onMakeCoffeeClicked = {
                scope.launch {
                    machine.makeCoffeByType(it)
                }
            },
            onPutMoney = machine::putMoney,
            onMoneyOff = machine::moneyOff,
            modifier = Modifier.padding(paddingValues)
        )
    }
    composable("resources") {
        ResourcesScreen(
            resources = resources,
            onPutMilk = machine::putMilk,
            onPutWater = machine::putWater,
            onPutBeans = machine::putBeans,
            onPutCash = machine::putMoney
        )
    }
}
```