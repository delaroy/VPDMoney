package com.vpd.vpdmobile.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import com.vpd.vpdmobile.databinding.FragmentTransactionItemsBinding

class TransactionAdapter : ListAdapter<TransactionHistory, ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentTransactionItemsBinding.inflate(
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

class ViewHolder(private val binding: FragmentTransactionItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TransactionHistory) {
        binding.apply {
            model = item
            executePendingBindings()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<TransactionHistory>() {

    override fun areItemsTheSame(oldItem: TransactionHistory, newItem: TransactionHistory): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TransactionHistory,
        newItem: TransactionHistory
    ): Boolean =
        oldItem == newItem
}
