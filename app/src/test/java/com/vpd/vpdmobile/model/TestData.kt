package com.vpd.vpdmobile.model

import com.vpd.vpdmobile.data.transaction.TransactionHistory

fun createTransactionData(): TransactionHistory =
    TransactionHistory(
        amount = "6,000",
        id = 1,
        status = "",
        messages = "",
        transactionTime = 789999
    )