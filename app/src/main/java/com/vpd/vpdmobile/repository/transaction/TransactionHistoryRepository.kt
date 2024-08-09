package com.vpd.vpdmobile.repository.transaction

import com.vpd.vpdmobile.data.transaction.TransactionHistory
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryRepository {

    fun getTransactions(): Flow<List<TransactionHistory>>

    suspend fun deleteAllTransactions(): Int

    suspend fun saveTransaction(transactionHistory: TransactionHistory): Long

}