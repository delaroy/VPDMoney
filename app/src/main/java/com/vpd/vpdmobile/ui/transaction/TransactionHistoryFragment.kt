package com.vpd.vpdmobile.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.vpd.vpdmobile.R
import com.vpd.vpdmobile.databinding.FragmentTransactionBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionHistoryFragment : Fragment(R.layout.fragment_transaction) {

    private var _binding : FragmentTransactionBinding? = null
    private val binding get() = _binding

    private val viewModel : TransactionHistoryViewModel by activityViewModels()
    private val transactionAdapter: TransactionAdapter by lazy { TransactionAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)

        viewModel.fetchAllTransactions()

        binding?.apply {
            transactionDetailsRecyclerview.apply {
                adapter = transactionAdapter
            }

            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

            transactionDetailsRecyclerview.addItemDecoration(
                DividerItemDecoration(
                    transactionDetailsRecyclerview.getContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            viewModel.allTransactions.observe(viewLifecycleOwner) { transactionList ->
                    transactionList?.let {
                        noTransaction.visibility = View.GONE
                        transactionAdapter.submitList(transactionList)
                    }?: binding?.apply {
                        noTransaction.visibility = View.VISIBLE
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
