package com.planetsistemas.cupcakeorders

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
fun GlobalScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = modifier,
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
        NavHost(
            navController = navController,
            startDestination = Screens.Start.name
        ) {
            composable(route = Screens.Start.name) {
                StartScreen(navController, innerPadding)
            }

            composable(route = Screens.Flavor.name) {
                FlavorScreen(
                    navController = navController,
                    innerPadding = innerPadding,
                    onCLickCancel = { _cancelOrder(navController) }
                )
            }

            composable(route = Screens.PickUp.name) {
                PickUpScreen(
                    navController = navController,
                    innerPadding = innerPadding,
                    onClickCancel = { _cancelOrder(navController) }
                )
            }

            composable(route = Screens.Summary.name) {
                SummaryScreen(
                    innerPadding = innerPadding,
                    onClickCancel = { _cancelOrder(navController) }
                )
            }
        }
    }
}

private fun _cancelOrder(navController: NavController) {
    //viewModel.resetOrder()

    // inclusive = false: clear history upper of screens
    navController.popBackStack(Screens.Start.name, inclusive = false)
}
