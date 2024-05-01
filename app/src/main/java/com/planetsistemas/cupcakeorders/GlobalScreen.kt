package com.planetsistemas.cupcakeorders

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Nombres definitivos para navegar entre las pantallas
 */
enum class Screens {
    Start,
    Flavor,
    PickUp,
    Summary
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalScreen(viewModel: OrderViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                //modifier = modifier,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.tertiary
                ),
                title = {
                    Text("Payasitos")
                },
            )
        }
    ) { innerPadding -> // not over topBar
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screens.Start.name
        ) {
            composable(route = Screens.Start.name) {
                StartScreen(
                    navController,
                    viewModel,
                    innerPadding)
            }

            composable(route = Screens.Flavor.name) {
                FlavorScreen(
                    navController = navController,
                    total = uiState.total,
                    viewModel = viewModel,
                    innerPadding = innerPadding,
                    onCLickCancel = { _cancelOrder(
                        navController = navController,
                        viewModel = viewModel
                    ) }
                )
            }

            composable(route = Screens.PickUp.name) {
                PickUpScreen(
                    navController = navController,
                    total = uiState.total,
                    viewModel = viewModel,
                    innerPadding = innerPadding,
                    onClickCancel = {
                        _cancelOrder(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                )
            }

            composable(route = Screens.Summary.name) {
                SummaryScreen(
                    uiState = uiState,
                    innerPadding = innerPadding,
                    onClickCancel = {
                        _cancelOrder(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun RowAmount(txt: String, amount: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = txt + "%.2f".format(amount),
            fontSize = 24.sp
        )
    }
}

private fun _cancelOrder(
    navController: NavController,
    viewModel: OrderViewModel
) {
    viewModel.resetOrder()

    // inclusive = false: clear history upper of screens
    navController.popBackStack(Screens.Start.name, inclusive = false)
}
