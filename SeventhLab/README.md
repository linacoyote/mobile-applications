# Navigation

Навигация в Jetpack Compose реализуется с помощью компонента NavHost,
который позволяет определить точки назначения и переходы между ними.

```kotlin
NavHost(
    navController = navHostController,
    modifier = Modifier.padding(paddingValues),
    startDestination = "start"
) {
    composable("start") {
        StartScreen(
            onStartChoice = {
                navHostController.navigate("choice")
            }
        )
    }
    composable("choice") {
        ChoiceScreen(
            onNavigateUp = navHostController::navigateUp,
            onChoiceChoose = { choice ->
                navHostController.navigateUp()
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(if (choice) "Да" else "Нет")
                }
            }
        )
    }
}
```
Когда пользователь запускает приложение, он попадает на точку назначения "start"
и видит экран StartScreen. Когда он нажимает на кнопку по центру, 
мы вызываем функцию navigate на navHostController и переходим на точку назначения "choice".

На точке назначения "choice" пользователь видит экран ChoiceScreen. Когда пользователь выбирает 
какой-либо вариант, мы вызываем функцию navigateUp на navHostController, 
чтобы вернуться на предыдущую точку назначения "start", а также функцию onChoiceChoose, которая возвращает true или false.
После этого запускаем снэкбар с сообщением о выборе.
