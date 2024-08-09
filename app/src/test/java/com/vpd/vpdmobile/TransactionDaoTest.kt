package com.vpd.vpdmobile

import com.vpd.vpdmobile.data.db.TransactionDao
import com.vpd.vpdmobile.model.createTransactionData
import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class TransactionDaoTest : DatabaseTest() {
    private lateinit var dao: TransactionDao

    @Before
    fun init() {
        dao = database.transactionDao
    }

    @Test
    fun `verify that transaction data is saved`() {
        val transaction = createTransactionData()
        val result = dao.saveTransaction(transaction = transaction)

        // then we verify that the transaction is saved
        Assert.assertEquals(result, 1)
    }
}


