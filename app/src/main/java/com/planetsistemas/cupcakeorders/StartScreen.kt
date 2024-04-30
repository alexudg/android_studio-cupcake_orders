package com.planetsistemas.cupcakeorders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val qtyList = listOf(
    Pair("1 Payasito", 1),
    Pair("6 Payasitos", 6),
    Pair("12 Payasitos", 12)
)

@Composable
fun StartScreen(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.cupcake),
                contentDescription = "Logo cupcake"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Nuevo pedido",
                fontSize = 30.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            qtyList.forEach { item ->
                BtnQty(
                    navController,
                    item.first
                )
            }
        }
    }
}

@Composable
fun BtnQty(navController: NavController, txt: String) {
    Button(
        modifier = Modifier.width(200.dp),
        onClick = { navController.navigate(Screens.Flavor.name) }
    ) {
        Text(text = txt)
    }
}