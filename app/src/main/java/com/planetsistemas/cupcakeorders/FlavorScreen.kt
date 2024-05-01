package com.planetsistemas.cupcakeorders

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    total: Double,
    viewModel: OrderViewModel,
    innerPadding: PaddingValues,
    onCLickCancel: () -> Unit
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from global screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            flavorList.forEach { item ->
                Row(
                    // select all width of row
                    modifier = Modifier
                        .selectable(
                            selected = selectedValue == item,
                            onClick = {
                                selectedValue = item
                            }
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                        }
                    )
                    Text(text = item)
                }
            }
            Divider(
                thickness = 6.dp
                //modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            RowAmount(txt = "TOTAL $", amount = total)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { onCLickCancel() }
            ) {
                Text("Cancelar")
            }
            Button(
                enabled = selectedValue.isNotEmpty(),
                onClick = {
                    viewModel.setFlavor(selectedValue)
                    navController.navigate(Screens.PickUp.name)
                }
            ) {
                Text("Siguiente")
            }
        }
    }
}
