# Площадь прямоугольника
## Прямоугольник
Работа с прямоугольником вынесена в отдельный класс ```Rectangle```
```kotlin
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
```
Класс имеют функцию ```area()```, которая возвращает площадь
## Интерфейс
Структура интерфейса выглядит так:
```kotlin
Scaffold(
    topBar = {
        TopAppBar(
            title = {
                Text("Калькулятор площади")
            }
        )
    }
) {
    Column{
        Row{
            OutlinedTextField(
                label = {
                    Text(text = "Задайте ширину")
                }
            )
        }
        Spacer()
        Row{
            OutlinedTextField(
                label = {
                    Text(text = "Задайте высоту")
                }
            )
        }
        Spacer(
        Button(
            onClick = {
               // вычисление
            }
        ) {
            Text(text = "Вычислить")
        }
        Text() // Ответ
    }
}
```

## Вывод ответа
```kotlin
 var answerText by remember { mutableStateOf("Задайте параметры") }
Button(
    onClick = {
        if (rectangle.width == null || rectangle.height == null) {
            showError = true
            answerText = "Задайте параметры"
        } else {
            answerText =
                "S = ${rectangle.width} * ${rectangle.height} = ${rectangle.area()} (мм2)"
        }
    }
) {
    Text(text = "Вычислить")
}
Text(answerText, style = MaterialTheme.typography.h5)
```