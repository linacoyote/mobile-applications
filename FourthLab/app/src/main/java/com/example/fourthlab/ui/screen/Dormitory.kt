package com.example.fourthlab.ui.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.example.fourthlab.R
import com.example.fourthlab.entity.Dormitory
import com.example.fourthlab.ui.components.ActionButton

@Composable
fun DormitoryScreen(
    dormitory: Dormitory,
    onCallClick: (String) -> Unit,
    onRouteClick: (String) -> Unit,
    onShareClick: (subject: String, text: String) -> Unit,
    onLikeClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.dormitory),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column {
                Text(text = dormitory.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = dormitory.address, fontSize = 14.sp, color = Color.Gray)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                IconButton(
                    onClick = {
                        onLikeClick(!dormitory.liked)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if (dormitory.liked) Color.Red else Color.Gray
                    )
                }
                Text(text = dormitory.likes.toString())
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            ActionButton(icon = Icons.Default.Call, text = "Позвонить") {
                onCallClick(dormitory.phone)
            }
            ActionButton(icon = Icons.Default.LocationOn, text = "Маршрут") {
                onRouteClick(dormitory.address)
            }
            ActionButton(icon = Icons.Default.Share, text = "Поделиться") {
                onShareClick(dormitory.name, dormitory.description)
            }
        }
        Text(text = dormitory.description, modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp))
    }
}