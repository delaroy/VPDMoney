package com.vpd.vpdmobile.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vpd.vpdmobile.R
import com.vpd.vpdmobile.data.db.UserAccounts
import com.vpd.vpdmobile.databinding.FragmentHomeBinding
import com.vpd.vpdmobile.ui.authentication.viewModel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel : AuthenticationViewModel by activityViewModels()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        getUser()
        UserAccounts.getInstance(requireContext()).retrieveAccountDetails()
        registerObserver()
        listenToChannels()

        binding?.apply {
            accountDetailsRecyclerview.apply {
                adapter = homeAdapter
            }

            floatingBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_transferFragment)
            }

            more.setOnClickListener {
                val popupMenu: PopupMenu = PopupMenu(requireContext(),more)
                popupMenu.menuInflater.inflate(R.menu.pop_up,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.transcation_history -> {
                            findNavController().navigate(R.id.action_homeFragment_to_transactionHistoryFragment)
                        }
                        R.id.log_out -> {
                            viewModel.signOut()
                        }
                    }
                    true
                })
                popupMenu.show()
            }
        }

        return binding?.root
    }

    private fun getUser() {
        viewModel.getCurrentUser()
    }

    private fun listenToChannels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allEventsFlow.collect { event ->
                when(event){
                    is AuthenticationViewModel.AllEvents.Message ->{
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun registerObserver() {
        viewModel.currentUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                observeTransactionDetails()
            }?: binding?.apply {
                findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
            }
        }
    }

    private fun observeTransactionDetails() {
        UserAccounts.getInstance(requireContext()).accountDetailsList.observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}