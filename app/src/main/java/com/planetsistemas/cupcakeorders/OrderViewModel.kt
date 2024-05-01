package com.planetsistemas.cupcakeorders

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val PRICE_CUPCAKE = 20.00
const val PRICE_PICKUP_SAME_DAY = 50.00

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setQuantity(qty: Int) {
        _uiState.update { currentState ->
            val subtotal = qty * PRICE_CUPCAKE
            currentState.copy(
                quantity = qty,
                subtotal = subtotal,
                total = subtotal
            )
        }
    }

    fun setFlavor(flavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = flavor)
        }
    }

    fun setDateAndTotal(date: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = date,
                total = calculateTotal(date = date)
            )
        }
    }

    private fun calculateTotal(date: String): Double {
        var total = _uiState.value.subtotal

        // is today, then sum price pickup same day
        if (date == pickupList()[0]) {
            total += PRICE_PICKUP_SAME_DAY
        }
        return total
    }

    /**
     * Reset the order state
     */
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }
}