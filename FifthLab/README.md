# Списки

В Jetpack Compose списки реализуются через функцию ```LazyColumn``` и ```LazyRow```.
Эта функция позволяет создавать динамические списки, состоящие из других 
Composable функций. Главным элементом этой функции является вызов ```items(List)```,
который принимает список и на его основе рисует интерфейс

## Список из трех
```kotlin
@Composable
fun SimpleList() {
    val items = List(3) {
        it
    }
    LazyColumn() {
        items(items = items) { item ->
            Column {
                Text(item.toString(), modifier = Modifier.padding(16.dp))
                Divider()
            }
        }
    }
}
```
## Список c номером строки
Когда первый видимый элемень имеет индекс меньше чем размер списка - 100, к списку
добавляется сто элементов.
```kotlin
@Composable
fun StringNumberList() {
    val list = remember { mutableStateListOf<Int>(10) }
    val lazyListState = rememberLazyListState()
    if (lazyListState.firstVisibleItemIndex >= list.size - 100 ) {
        list.addAll(List(100) { it })
    }
    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
        itemsIndexed(list) { index, item ->
            Text("строка $index")
        }
    }
}
```

## Степени двойки
```kotlin
@Composable
fun TwoPowerList() {
    val list = remember { mutableStateListOf<Int>(10) }
    val lazyListState = rememberLazyListState()
    if (lazyListState.firstVisibleItemIndex >= list.size - 40 ) {
        list.addAll(List(30) { it })
    }
    LazyColumn(state = lazyListState) {
        itemsIndexed(list) { index, item ->
            Text(
                text = "2 ^ $index = ${2.toDouble().pow(index.toDouble()).toLong()}",
                modifier = Modifier.padding(16.dp)
            )
            Divider()
        }
    }
}
```