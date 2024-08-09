package com.vpd.vpdmobile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transaction: TransactionHistory): Long

    @Query("SELECT * FROM `transactionHistory` ORDER BY transactionTime DESC")
    fun getAllTransactions(): Flow<List<TransactionHistory>>

    @Query("DELETE FROM `transactionHistory`")
    fun deleteAllTransactions(): Int
}