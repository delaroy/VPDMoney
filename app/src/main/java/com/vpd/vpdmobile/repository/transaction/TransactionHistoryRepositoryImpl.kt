package com.vpd.vpdmobile.repository.transaction

import com.vpd.vpdmobile.data.db.TransactionDao
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionHistoryRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionHistoryRepository {

    override fun getTransactions(): Flow<List<TransactionHistory>> =
        dao.getAllTransactions().map {
            it
        }

    override suspend fun deleteAllTransactions(): Int =
        dao.deleteAllTransactions()

    override suspend fun saveTransaction(transactionHistory: TransactionHistory): Long =
        dao.saveTransaction(transactionHistory)
}