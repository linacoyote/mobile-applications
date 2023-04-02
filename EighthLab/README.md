# Работа с сетью

В этой лабораторной работе были использованы две библиотеки - Retrofit и Coil, для загрузки данных с
API и изображений соответственно, в приложении Jetpack Compose.

## Retrofit

Retrofit - это библиотека для работы с RESTful API в Android. Она упрощает создание клиента для
работы с веб-сервисами, используя аннотации, которые облегчают написание кода и
уменьшают количество бойлерплейта.
В этой лабораторной работе была использована Retrofit для загрузки новостей из API.
Для этого были выполнены следующие шаги:

1. Определен интерфейс для работы с API:

```kotlin
interface NewsApiService {
    @GET("getNews.php")
    suspend fun getNews(@Query("key") key: String): List<News>
} 
```

2. Создан экземпляр Retrofit и настроен клиент:

```kotlin
   val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val newsApiService = retrofit.create(NewsApiService::class.java)
```

3. Вызван метод API для загрузки новостей:

```kotlin
val newsList = NewsApi.newsApiService.getNews("6df2f5d38d4e16b5a923a6d4873e2ee295d0ac90")
```

## Coil

Coil - это библиотека для загрузки изображений в Android. Она позволяет загружать
изображения из различных источников, таких как URL, ресурсы и т.д.
В этой лабораторной работе была использована Coil
для загрузки превью новостей. Для этого вызывается функция ```rememberAsyncImagePainter()```

```kotlin
Image(
    rememberAsyncImagePainter(
        model = news.previewPictureSrc,
        imageLoader = loader
    ),
    contentDescription = null,
    modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .clip(RoundedCornerShape(8.dp))
)
```