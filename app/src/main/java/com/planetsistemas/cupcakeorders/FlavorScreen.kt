package com.planetsistemas.cupcakeorders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

val flavorList = listOf(
    "Vainilla",
    "Chocolate",
    "Mermelada",
    "Caramelo salado",
    "Café",
    "Especial"
)

@Composable
fun FlavorScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    onCLickCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("SABORES SCREEN")
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            OutlinedButton(
                onClick = { onCLickCancel() }
            ) {
                Text("Cancelar")
            }
            Button(
                onClick = { navController.navigate(Screens.PickUp.name) }
            ) {
                Text("Siguiente")
            }
        }
    }
}
