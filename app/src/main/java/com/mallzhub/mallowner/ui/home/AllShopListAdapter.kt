package com.mallzhub.mallowner.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mallzhub.mallowner.AppExecutors
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.AllShopListItemBinding
import com.mallzhub.mallowner.models.LevelWiseShops
import com.mallzhub.mallowner.models.MallMerchant
import com.mallzhub.mallowner.models.Merchant

import com.mallzhub.mallowner.models.PaymentMethod
import com.mallzhub.mallowner.util.DataBoundListAdapter

class AllShopListAdapter(
    private val appExecutors: AppExecutors,
    private val actionCallBack: ShopListAdapter.ShopListActionCallback,
    private val itemCallback: ((MallMerchant) -> Unit)? = null
) : DataBoundListAdapter<LevelWiseShops, AllShopListItemBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<LevelWiseShops>() {
        override fun areItemsTheSame(oldItem: LevelWiseShops, newItem: LevelWiseShops): Boolean {
            return oldItem.level?.id == newItem.level?.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: LevelWiseShops,
            newItem: LevelWiseShops
        ): Boolean {
            return oldItem.level == newItem.level
        }

    }) {

    override fun createBinding(parent: ViewGroup): AllShopListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_all_shops, parent, false
        )
    }


    override fun bind(binding: AllShopListItemBinding, position: Int) {
        val allShopItem = getItem(position)
        binding.level = allShopItem.level?.name

        val shopListAdapter = ShopListAdapter(
            appExecutors, actionCallBack, itemCallback
        )

        binding.rvShopList.adapter = shopListAdapter

        shopListAdapter.submitList(allShopItem.shops)
    }
}