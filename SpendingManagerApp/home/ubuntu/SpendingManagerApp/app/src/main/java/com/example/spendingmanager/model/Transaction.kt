
package com.example.spendingmanager.model

import java.util.Date

// Data class to represent a single transaction
data class Transaction(
    val id: String = "", // Unique ID for the transaction
    val itemDetails: String = "", // Description of the purchase
    val amount: Double = 0.0, // Amount of the transaction
    val timestamp: Date = Date() // Timestamp of the transaction
)

