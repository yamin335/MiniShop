package com.mallzhub.mallowner.ui.home

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mallzhub.mallowner.AppExecutors
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.ShoppingMallListItemBinding

import com.mallzhub.mallowner.models.PaymentMethod
import com.mallzhub.mallowner.models.ShoppingMall
import com.mallzhub.mallowner.util.DataBoundListAdapter

class MallsListAdapter(
    private val appExecutors: AppExecutors,
    private val itemCallback: ((ShoppingMall) -> Unit)? = null

) : DataBoundListAdapter<ShoppingMall, ShoppingMallListItemBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<ShoppingMall>() {
        override fun areItemsTheSame(oldItem: ShoppingMall, newItem: ShoppingMall): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: ShoppingMall,
            newItem: ShoppingMall
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    val onClicked = MutableLiveData<PaymentMethod>()
    override fun createBinding(parent: ViewGroup): ShoppingMallListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_shopping_mall, parent, false
        )
    }


    override fun bind(binding: ShoppingMallListItemBinding, position: Int) {
        val item = getItem(position)
        binding.mall = item

        binding.root.setOnClickListener {
            itemCallback?.invoke(item)
        }

        binding.imageRequestListener = object: RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                binding.logo.setImageResource(R.drawable.shopping_mall)
                return true
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }
        }
    }
}