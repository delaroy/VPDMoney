package com.vpd.vpdmobile.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vpd.vpdmobile.R
import com.vpd.vpdmobile.data.db.UserAccounts
import com.vpd.vpdmobile.databinding.FragmentTransferBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment(R.layout.fragment_transfer) {

    private var _binding : FragmentTransferBinding? = null
    private val binding get() = _binding
    private val accountDetails = arrayListOf<Long>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)

        UserAccounts.getInstance(requireContext()).accountDetailsList.observe(viewLifecycleOwner) {
            it.map { item ->
                accountDetails.add(item.accountNumber)
            }
        }

        binding?.apply {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_list_item_1, accountDetails)
            fromUserText.setAdapter(adapter)
            toUserText.setAdapter(adapter)

            toolbarTransfer.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            transferButton.setOnClickListener {
                val fromUser = fromUserText.text.toString().trim()
                val toUser = toUserText.text.toString().trim()
                val amount = amountEdit.text.toString().trim()

                when {
                    fromUser.isEmpty() -> {
                        fromUserLayout.isErrorEnabled = true
                        fromUserLayout.error = "From account detail is required"
                    }

                    toUser.isEmpty() -> {
                        toUserLayout.isErrorEnabled = true
                        toUserLayout.error = "To account detail is required"
                    }

                    amount.isEmpty()  -> {
                        amountTextLayout.isErrorEnabled = true
                        amountTextLayout.error = "Amount is required"
                    }

                    else -> {
                        findNavController().navigate(TransferFragmentDirections.actionTransferFragmentToTransferDetailsFragment(fromAccount = fromUser, toAccount = toUser, amount = amount))
                    }
                }
            }
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}