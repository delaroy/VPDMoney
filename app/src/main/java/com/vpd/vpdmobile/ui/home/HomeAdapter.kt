package com.vpd.vpdmobile.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vpd.vpdmobile.data.model.AccountDetails
import com.vpd.vpdmobile.databinding.FragmentHomeItemsBinding

class HomeAdapter : ListAdapter<AccountDetails, ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentHomeItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item.let {
            holder.apply {
                bind(item)
                itemView.tag = item
            }
        }

    }
}

class ViewHolder(private val binding: FragmentHomeItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AccountDetails) {
        binding.apply {
            model = item
            executePendingBindings()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<AccountDetails>() {

    override fun areItemsTheSame(oldItem: AccountDetails, newItem: AccountDetails): Boolean =
        oldItem.accountBalance == newItem.accountBalance

    override fun areContentsTheSame(
        oldItem: AccountDetails,
        newItem: AccountDetails
    ): Boolean =
        oldItem == newItem
}

interface UserDetailClick {
    fun clickOnItem(data: AccountDetails)
}