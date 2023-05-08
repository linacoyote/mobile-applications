package com.example.coffemachine.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffemachine.model.Resources

@Composable
fun ResourcesScreen(
    resources: Resources,
    onPutMilk: (Int) -> Unit,
    onPutWater: (Int) -> Unit,
    onPutBeans: (Int) -> Unit,
    onPutCash: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFBCDF18))
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.5f))
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resources",
                    style = MaterialTheme.typography.h4
                )
                Text(text = "Milk: ${resources.milk}")
                Text(text = "Water: ${resources.water}")
                Text(text = "Beans: ${resources.coffeeBeans}")
                Text(text = "Cash: ${resources.cash}", fontSize = 18.sp)
            }
        }
        var milk by remember { mutableStateOf("") }
        var milkError by remember { mutableStateOf(false) }
        var water by remember { mutableStateOf("") }
        var waterError by remember { mutableStateOf(false) }
        var beans by remember { mutableStateOf("") }
        var beansError by remember { mutableStateOf(false) }
        var cash by remember { mutableStateOf("") }
        var cashError by remember { mutableStateOf(false) }
        TextField(
            value = milk,
            onValueChange = { milk = it; milkError = false },
            placeholder = { Text("put milk") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        TextField(
            value = water,
            onValueChange = { water = it; waterError = false },
            placeholder = { Text("put water") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        TextField(
            value = beans,
            onValueChange = { beans = it; beansError = false },
            placeholder = { Text("put beans") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        TextField(
            value = cash,
            onValueChange = { cash = it; cashError = false },
            placeholder = { Text("put cash") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
        )
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    try {
                        onPutMilk(milk.toInt())
                    } catch (t: Throwable) {
                        milkError = true
                    }
                    try {
                        onPutWater(water.toInt())
                    } catch (t: Throwable) {
                        waterError = true
                    }
                    try {
                        onPutBeans(beans.toInt())
                    } catch (t: Throwable) {
                        beansError = true
                    }
                    try {
                        onPutCash(cash.toInt())
                    } catch (t: Throwable) {
                        cashError = true
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF49E762), contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    try {
                        onPutMilk(-milk.toInt())
                    } catch (t: Throwable) {
                        milkError = true
                    }
                    try {
                        onPutWater(-water.toInt())
                    } catch (t: Throwable) {
                        waterError = true
                    }
                    try {
                        onPutBeans(-beans.toInt())
                    } catch (t: Throwable) {
                        beansError = true
                    }
                    try {
                        onPutCash(-cash.toInt())
                    } catch (t: Throwable) {
                        cashError = true
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFEC2538), contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = null)
            }
        }
    }
}