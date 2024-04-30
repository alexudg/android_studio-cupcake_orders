package com.planetsistemas.cupcakeorders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SummaryScreen(
    innerPadding: PaddingValues,
    onClickCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("RESUMEN DEL PEDIDO SCREEN")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Enviar pedido a otra app")
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClickCancel() }
            ) {
                Text("Cancelar")
            }
        }
    }
}