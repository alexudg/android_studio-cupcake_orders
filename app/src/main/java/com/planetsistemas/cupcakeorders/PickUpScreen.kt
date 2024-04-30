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

@Composable
fun PickUpScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    onClickCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("FECHA ENTREGA SCREEN")
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            OutlinedButton(
                onClick = { onClickCancel() }
            ) {
                Text("Cancelar")
            }
            Button(
                onClick = { navController.navigate(Screens.Summary.name) }
            ) {
                Text("Siguiente")
            }
        }
    }
}