package com.vpd.vpdmobile

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vpd.vpdmobile.data.db.TransactionDao
import com.vpd.vpdmobile.model.createTransactionData
import com.vpd.vpdmobile.repository.transaction.TransactionHistoryRepository
import com.vpd.vpdmobile.repository.transaction.TransactionHistoryRepositoryImpl
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

internal class TransactionHistoryRepositoryImplTest: DatabaseTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dao: TransactionDao = mock()

    private lateinit var transactionHistory: TransactionHistoryRepository

    @Before
    fun setUp() {
        transactionHistory = TransactionHistoryRepositoryImpl(dao)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `verify all transactions are called`()  = runTest {
        whenever(dao.getAllTransactions())
            .thenReturn(flowOf(listOf(createTransactionData())))

        //when we get the transaction list from the repository
        val result = transactionHistory.getTransactions()

        //verify that the transaction cache is called
        verify(dao).getAllTransactions()

        //then assert that result has value
        Assert.assertNotNull(result)
    }
}