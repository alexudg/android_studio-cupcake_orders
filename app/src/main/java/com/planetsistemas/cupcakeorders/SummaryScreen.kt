package com.planetsistemas.cupcakeorders

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(
    uiState: OrderUiState,
    innerPadding: PaddingValues,
    onClickCancel: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "RESUMEN DEL PEDIDO")
            Divider(
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "CANTIDAD")
            Text(text = uiState.quantity.toString())
            Divider(modifier = Modifier.padding(8.dp))
            Text(text = "SABOR")
            Text(text = uiState.flavor)
            Divider(modifier = Modifier.padding(8.dp))
            Text(text = "ENTREGA")
            Text(uiState.date)
            Divider(modifier = Modifier.padding(8.dp))
            if (uiState.subtotal < uiState.total) {
                RowAmount(txt = "SUBTOTAL ", amount = uiState.subtotal)
                RowAmount(txt = "ENTREGA MISMO DIA ", amount = PRICE_PICKUP_SAME_DAY)
            }
            RowAmount(txt = "TOTAL $", amount = uiState.total)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { shareOrder(context, uiState) }
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

fun shareOrder(context: Context, uiState: OrderUiState) {
    val subject = "Nueva orden de Payasitos"

    // create summary
    val summary = String.format(
        "Cantidad: %d \n Sabor: %s \n Fecha de entrega: %s \n TOTAL: %s",
        uiState.quantity,
        uiState.flavor,
        uiState.date,
        "%.2f".format(uiState.total)
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            subject
        )
    )
}