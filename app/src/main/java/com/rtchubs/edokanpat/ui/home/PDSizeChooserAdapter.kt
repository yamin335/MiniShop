package com.rtchubs.edokanpat.ui.home

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rtchubs.edokanpat.AppExecutors
import com.rtchubs.edokanpat.R
import com.rtchubs.edokanpat.databinding.*
import com.rtchubs.edokanpat.models.Merchant

import com.rtchubs.edokanpat.models.PaymentMethod
import com.rtchubs.edokanpat.models.Product
import com.rtchubs.edokanpat.util.DataBoundListAdapter
import com.rtchubs.edokanpat.util.colorList

class PDSizeChooserAdapter(
    private val appExecutors: AppExecutors,
    private val itemCallback: ((String) -> Unit)? = null

) : DataBoundListAdapter<String, PDSizeChooserBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    // Properties
    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    val onClicked = MutableLiveData<String>()
    private var selectedItemIndex = -1

    override fun createBinding(parent: ViewGroup): PDSizeChooserBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_product_details_size_chooser, parent, false
        )
    }


    override fun bind(binding: PDSizeChooserBinding, position: Int) {
        val item = getItem(position)

        binding.sizeIndicatorText.text = item

        if (selectedItemIndex == position) {
            binding.sizeIndicator.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#E91E63")))
            binding.sizeIndicatorText.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")))
        } else {
            binding.sizeIndicator.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#DDDDDD")))
            binding.sizeIndicatorText.setTextColor(ColorStateList.valueOf(Color.parseColor("#555555")))
        }

        binding.root.setOnClickListener {
            selectedItemIndex = position
            itemCallback?.invoke(item)
            notifyDataSetChanged()
        }
    }
}