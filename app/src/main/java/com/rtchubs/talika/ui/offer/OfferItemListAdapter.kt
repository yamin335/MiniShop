package com.rtchubs.talika.ui.offer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.rtchubs.talika.AppExecutors
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.ShopWiseOfferListItemBinding
import com.rtchubs.talika.models.MerchantWiseOffer
import com.rtchubs.talika.models.OfferProduct
import com.rtchubs.talika.util.DataBoundListAdapter

class OfferItemListAdapter(
    private val appExecutors: AppExecutors,
    private val productSelectionCallback: (OfferProduct) -> Unit

) : DataBoundListAdapter<MerchantWiseOffer, ShopWiseOfferListItemBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<MerchantWiseOffer>() {
        override fun areItemsTheSame(oldItem: MerchantWiseOffer, newItem: MerchantWiseOffer): Boolean {
            return oldItem.merchantId == newItem.merchantId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MerchantWiseOffer,
            newItem: MerchantWiseOffer
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup): ShopWiseOfferListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_offer, parent, false
        )
    }

    override fun bind(binding: ShopWiseOfferListItemBinding, position: Int) {
        val item = getItem(position)

        val offerProductListAdapter = OfferProductListAdapter(appExecutors) {
            productSelectionCallback.invoke(it)
        }

        binding.rvOfferItems.setHasFixedSize(true)
        binding.rvOfferItems.adapter = offerProductListAdapter
        offerProductListAdapter.submitList(item.offerProductList)
        binding.shopName = item.merchantName
    }
}