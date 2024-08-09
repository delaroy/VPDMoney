package com.vpd.vpdmobile.data.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionHistory")
data class TransactionHistory(
    val amount: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: String,
    val messages: String,
    val transactionTime: Long
)