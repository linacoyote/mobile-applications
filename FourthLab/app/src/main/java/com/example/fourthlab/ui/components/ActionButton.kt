package com.example.fourthlab.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Box(modifier = modifier.clickable(onClick = action).padding(4.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text.uppercase(), color = MaterialTheme.colors.primary, fontSize = 14.sp)
        }
    }
}