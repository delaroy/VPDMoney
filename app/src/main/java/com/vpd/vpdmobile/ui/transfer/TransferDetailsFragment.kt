package com.vpd.vpdmobile.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vpd.vpdmobile.R
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import com.vpd.vpdmobile.data.db.UserAccounts
import com.vpd.vpdmobile.databinding.FragmentTransferDetailsBinding
import com.vpd.vpdmobile.ui.transaction.TransactionHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class TransferDetailsFragment : Fragment(R.layout.fragment_transfer_details) {

    private val viewModel : TransactionHistoryViewModel by activityViewModels()
    private var _binding : FragmentTransferDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferDetailsBinding.inflate(inflater, container, false)

        binding?.apply {
            fromAccountNumber.text = args.fromAccount
            amountValue.text = args.amount
            toAccountNumber.text = args.toAccount

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            transferButton.setOnClickListener {
                val response = UserAccounts.getInstance(requireContext()).processPayment(
                    fromUser = args.fromAccount.toLong(),
                    toUser = args.toAccount.toLong(),
                    amount = args.amount.toDouble()
                )

                if (response.success) {
                    viewModel.saveTransactionHistory(transactionHistory = TransactionHistory(
                        amount = args.amount,
                        messages = "Being transfer of ${"₦" + NumberFormat.getNumberInstance(Locale.US).format(args.amount)} from Acct no: ${args.fromAccount} to Acct no: ${args.toAccount}",
                        transactionTime = Date().time,
                        status = "Successful",
                        id = 0
                    ))
                } else {
                    viewModel.saveTransactionHistory(transactionHistory = TransactionHistory(
                        amount = args.amount,
                        messages = response.messages,
                        transactionTime = System.currentTimeMillis(),
                        status = "Failed",
                        id = 0
                    ))
                }
                Toast.makeText(requireContext(), response.messages, Toast.LENGTH_SHORT).show()
                findNavController().navigate(TransferDetailsFragmentDirections.actionTransferDetailsFragmentToHomeFragment())
            }
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val args : TransferDetailsFragmentArgs by navArgs()
}