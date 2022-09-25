package com.rtchubs.talika.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.rtchubs.talika.AppExecutors
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.AllShopListItemBinding
import com.rtchubs.talika.models.LevelWiseShops
import com.rtchubs.talika.models.MallMerchant

import com.rtchubs.talika.util.DataBoundListAdapter

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