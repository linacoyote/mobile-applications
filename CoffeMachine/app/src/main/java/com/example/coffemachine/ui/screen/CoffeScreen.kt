package com.example.coffemachine.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffemachine.model.Coffee
import com.example.coffemachine.model.Resources

@Composable
fun CoffeeScreen(
    resources: Resources,
    onMakeCoffeeClicked: (Coffee) -> Unit,
    onPutMoney: (Int) -> Unit,
    onMoneyOff: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var currentCoffee by remember { mutableStateOf(Coffee.Americano) }
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFBCDF18))
                .padding(20.dp)
        ) {
            Text(
                text = "Beans: ${currentCoffee.coffeeBeans()}",
                style = MaterialTheme.typography.caption
            )
            Text(text = "Milk: ${currentCoffee.milk()}", style = MaterialTheme.typography.caption)
            Text(text = "Water: ${currentCoffee.water()}", style = MaterialTheme.typography.caption)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.5f))
                    .padding(vertical = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Coffee Maker",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = "Your money: ${resources.cash}", fontSize = 18.sp)
            }
        }
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Coffee.values().forEach { coffee ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = currentCoffee == coffee,
                            onClick = { currentCoffee = coffee }
                        )
                        Text(text = coffee.name.lowercase())
                    }
                }
            }
            Button(
                onClick = {
                    onMakeCoffeeClicked(currentCoffee)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(
                        0xFF3A97A3
                    ), contentColor = Color.Black
                ),
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
            }
        }
        Divider(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var money by remember { mutableStateOf("") }
            var isError by remember {
                mutableStateOf(false)
            }
            TextField(
                value = money,
                onValueChange = {
                    isError = false
                    money = it
                },
                placeholder = { Text("Put money here") },
                isError = isError,
                modifier = Modifier.padding(end = 8.dp).weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)
            )
            Button(
                onClick = {
                    try {
                        onPutMoney(money.toInt())
                    } catch (t: Throwable) {
                        isError = true
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF9ADFA5), contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.AttachMoney, contentDescription = null)
            }
            Button(
                onClick = {
                    try {
                        onMoneyOff(money.toInt())
                    } catch (t: Throwable) {
                        isError = true
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE78891), contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.MoneyOff, contentDescription = null)
            }
        }
    }
}