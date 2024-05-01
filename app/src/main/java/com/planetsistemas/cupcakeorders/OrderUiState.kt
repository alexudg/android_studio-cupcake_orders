package com.planetsistemas.cupcakeorders

data class OrderUiState (
        /** Selected cupcake quantity (1, 6, 12) */
        val quantity: Int = 0,
        /** Flavor of the cupcakes in the order (such as "Chocolate", "Vanilla", etc..) */
        val flavor: String = "",
        /** Selected date for pickup (such as "Jan 1") */
        val date: String = "",
        /** SubTotal price for the order */
        val subtotal: Double = 0.00,
        /** Total price for the order */
        val total: Double = 0.00
)