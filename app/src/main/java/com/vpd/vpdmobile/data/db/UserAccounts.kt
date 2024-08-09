package com.vpd.vpdmobile.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vpd.vpdmobile.data.model.AccountDetails
import com.vpd.vpdmobile.data.model.PaymentResponse


class UserAccounts private constructor(context: Context) {

    private val accountDetails = HashMap<Long, AccountDetails>()

    private val _accountDetailsList = MutableLiveData<List<AccountDetails>>()
    var accountDetailsList: LiveData<List<AccountDetails>> = _accountDetailsList

    companion object {
        const val TAG = "UserAccounts"

        @Volatile
        private var INSTANCE: UserAccounts? = null

        fun getInstance(context: Context): UserAccounts =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserAccounts(context).also { INSTANCE = it }
            }
    }

    init {
        if (accountDetails.isEmpty()) {
            addAccounts()
        }
        retrieveAccountDetails()
    }

    private fun addAccounts() {
        accountDetails[6329567458] = AccountDetails(fullName = "Mike Peters", accountNumber = 6329567458, accountBalance = 2000.0)
        accountDetails[6230947812] = AccountDetails(fullName = "Ojo Rahman", accountNumber = 6230947812, accountBalance = 8000.0)
    }

    fun processPayment(
        fromUser: Long,
        toUser: Long,
        amount: Double
    ): PaymentResponse {
        val fromUserDetails = accountDetails[fromUser]
        val toUserDetails = accountDetails[toUser]

        if (fromUserDetails == null) {
            return PaymentResponse(success = false, code = 501, messages = "User Details not found.")
        }

        if (toUserDetails == null) {
            return PaymentResponse(success = false, code = 501, messages = "User Details not found.")
        }

        if (fromUser == toUser) {
            return PaymentResponse(success = false, code = 501, messages = "Transfer can't be made to same account")
        }

        val fromUserBalance: Double = fromUserDetails.accountBalance
        if (fromUserBalance < amount) {
            println("Insufficient funds.")
            return PaymentResponse(success = false, code = 501, messages = "Insufficient funds.")
        }
        fromUserDetails.accountBalance = fromUserDetails.accountBalance - amount
        accountDetails[fromUser] = fromUserDetails

        toUserDetails.accountBalance = toUserDetails.accountBalance + amount
        accountDetails[toUser] = toUserDetails
        println("Payment processed successfully.")
        return PaymentResponse(success = true, code = 200, messages = "Payment processed successfully.")
    }

    fun retrieveAccountDetails() {
        val accountList = ArrayList(accountDetails.values)
        _accountDetailsList.value = accountList
    }
}
