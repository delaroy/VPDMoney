package com.vpd.vpdmobile.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import com.vpd.vpdmobile.repository.transaction.TransactionHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(
    private val repository : TransactionHistoryRepository
) : ViewModel() {

    private val _allTransactions =
        MutableLiveData<List<TransactionHistory>>()
    val allTransactions : LiveData<List<TransactionHistory>> = _allTransactions

    fun fetchAllTransactions() {
        viewModelScope.launch {
            repository.getTransactions()
                .collect { transactions ->
                    _allTransactions.value = transactions
                }
        }
    }

    fun saveTransactionHistory (transactionHistory: TransactionHistory) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveTransaction(transactionHistory)
        }
    }
}