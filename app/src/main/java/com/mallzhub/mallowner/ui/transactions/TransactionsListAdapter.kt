package com.mallzhub.mallowner.ui.transactions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mallzhub.mallowner.AppExecutors
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.TransactionListItemBinding
import com.mallzhub.mallowner.models.order.SalesData
import com.mallzhub.mallowner.util.DataBoundListAdapter

class TransactionsListAdapter(
    private val appExecutors: AppExecutors,
    private val itemCallback: ((SalesData) -> Unit)? = null
) : DataBoundListAdapter<SalesData, TransactionListItemBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<SalesData>() {
        override fun areItemsTheSame(oldItem: SalesData, newItem: SalesData): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: SalesData,
            newItem: SalesData
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup): TransactionListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_transaction, parent, false
        )
    }


    override fun bind(binding: TransactionListItemBinding, position: Int) {
        val item = getItem(position)
        binding.invoice = item.OurReference ?: "Undefined Invoice"
        binding.date = item.date ?: "No date found"
        binding.subTotal = "${binding.root.context.getString(R.string.sign_taka)} ${item.grand_total?.toString() ?: "0"}"

        binding.root.setOnClickListener {
            itemCallback?.invoke(item)
        }
    }
}