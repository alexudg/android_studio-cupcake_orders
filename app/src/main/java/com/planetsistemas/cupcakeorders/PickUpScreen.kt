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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun pickupList(): List<String> {
    val dateOptions = mutableListOf<String>()
    val formatter = SimpleDateFormat("EEEE dd MMMM", Locale("es", "MX"))
    //val formatter = SimpleDateFormat("E d MMM", Locale.getDefault())
    val calendar = Calendar.getInstance()
    // add current date and the following 3 dates.
    repeat(4) {
        dateOptions.add(formatter.format(calendar.time))
        calendar.add(Calendar.DATE, 1)
    }
    return dateOptions
}

@Composable
fun PickUpScreen(
    navController: NavController,
    total: Double,
    viewModel: OrderViewModel,
    innerPadding: PaddingValues,
    onClickCancel: () -> Unit
) {
    val pickupList = pickupList()
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), // padding from glogal screen
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            pickupList.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = selectedValue == item,
                            onClick = {
                                selectedValue = item
                            }
                        )
                        .fillMaxWidth()
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                        }
                    )
                    Text(item)
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
        )
        {
            OutlinedButton(
                onClick = { onClickCancel() }
            ) {
                Text("Cancelar")
            }
            Button(
                enabled = selectedValue.isNotEmpty(),
                onClick = {
                    viewModel.setDateAndTotal(selectedValue)
                    navController.navigate(Screens.Summary.name)
                }
            ) {
                Text("Siguiente")
            }
        }
    }
}