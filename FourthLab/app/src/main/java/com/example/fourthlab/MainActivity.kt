package com.example.fourthlab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.fourthlab.entity.Dormitory
import com.example.fourthlab.ui.screen.DormitoryScreen
import com.example.fourthlab.ui.theme.FourthLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FourthLabTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Общежития КубГАУ")
                            },
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    }
                ) { innerPadding ->
                    var dormitory by remember { mutableStateOf(getDormitory()) }
                    DormitoryScreen(
                        dormitory = dormitory,
                        onCallClick = { number ->
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:$number")
                            }
                            startActivity(intent)
                        },
                        onRouteClick = { address ->
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("google.navigation:q=$address")
                            }
                            startActivity(intent)
                        },
                        onShareClick = { subject, text ->
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, subject)
                                putExtra(Intent.EXTRA_TEXT, text)
                            }
                            val shareIntent = Intent.createChooser(intent, null)
                            startActivity(shareIntent)
                        },
                        onLikeClick = { liked ->
                            val likes = dormitory.likes + if (liked) 1 else -1
                            dormitory = dormitory.copy(likes = likes, liked = liked)
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

fun getDormitory(): Dormitory {
    return Dormitory(
        name = "Общежитие №18",
        address = "Краснодар, ул.Калинина 13",
        liked = true,
        likes = 28,
        description = "Факультет прикладной информатики организован в 2000 году для подготовки специалистов в области информационных технологий.  Обучение на факультете проводится по направлениям: «Прикладная информатика», «Бизнес-информатика» и «Информационные системы и технологии». Гарантией востребованности наших специалистов является возрастающее значение информационных технологий в современном обществе.\n" +
                "\n" +
                "«Информационные системы и технологии»:  специальность направлена на подготовку профессионалов в области разработки, создания и эксплуатации комплексных интегрированных информационно-управляющих систем автоматизации предприятий и учреждений самого различного профиля и самых различных размеров для решения задач их комплексной атоматизации.",
        phone = "89181889889",
        imageId = R.drawable.dormitory
    )
}