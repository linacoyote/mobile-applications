# Jetpack Compose

Jetpack Compose - фреймворк для разработки пользовательского интерфейса для приложений на Android. Он позволяет создавать интерфейс, используя декларативный подход.

Ссылки на документацию

[Jetpack Compose - официальная документация](https://developer.android.com/jetpack/compose)

Описание примера компонента
```kotlin
@Composable
fun JetpackCompose () {
		Card var expanded by remember { mutableStateof(false) }
		Column(Modifier.clickable { expanded = !expanded }) {
				Image (painterResource(R.drawable. jetpack_compose))
				AnimatedVisibility (expanded) {
						Text(
								text = "Jetpack Compose"
								style = MaterialTheme.typography.bodyLarge,
						)
				}
		}
}

```

![Sample](https://developer.android.com/static/images/jetpack/compose/landing-preview-animation.gif)

Пример TextField. Компонент представляет собой поле ввода текста, которое позволяет пользователю вводить текст. Вот некоторые из особенностей, которые предоставляет TextField:
Код с примером использования компонента

Вот как выглядит пример кода, который демонстрирует использование TextField в Jetpack Compose:

```kotlin
@Composable
fun MyScreen() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Введите текст") }
    )
}
```

Код из документации:
```kotlin
/** * Display a list of names the user can click with a header */
@Composable
fun NamePicker(
	header: String,
	names: List<String>,
	onNameClicked: (String) -> Unit)
) {    
	Column {        
		// this will recompose when [header] changes, but not when [names] changes        
		Text(header, style = MaterialTheme.typography.h5)        
		Divider()       
		// LazyColumn is the Compose version of a RecyclerView.        
		// The lambda passed to items() is similar to a RecyclerView.ViewHolder.        
		LazyColumn { 
			items(names) { name ->      
		// When an item's [name] updates, the adapter for that item       
		// will recompose. This will not recompose when [header] changes                
				NamePickerItem(name, onNameClicked)            
			}        
		}    
	}
}
/** * Display a single name the user can click. */
@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {    
	Text(
		name, 
		Modifier.clickable(
			onClick = { onClicked(name) }
		)
	)
}
```

