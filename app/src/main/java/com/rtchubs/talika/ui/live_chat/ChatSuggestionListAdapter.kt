package com.rtchubs.talika.ui.live_chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.rtchubs.talika.AppExecutors
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.*

import com.rtchubs.talika.util.DataBoundListAdapter

class ChatSuggestionListAdapter(
    private val appExecutors: AppExecutors,
    private val itemCallback: ((String) -> Unit)? = null

) : DataBoundListAdapter<String, ChatSuggestionListItemBinding>(
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

    //private var selectedItemIndex = -1

    override fun createBinding(parent: ViewGroup): ChatSuggestionListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_chat_suggestion, parent, false
        )
    }


    override fun bind(binding: ChatSuggestionListItemBinding, position: Int) {
        val item = getItem(position)

        binding.categoryName.text = item

//        if (selectedItemIndex == position) {
//            binding.cardCategory.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.themeColor))
//            binding.categoryName.setTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")))
//        } else {
//            binding.cardCategory.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")))
//            binding.categoryName.setTextColor(ColorStateList.valueOf(Color.parseColor("#555555")))
//        }

        binding.root.setOnClickListener {
            //selectedItemIndex = position
            itemCallback?.invoke(item)
            //notifyDataSetChanged()
        }
    }

//    fun resetList() {
//        selectedItemIndex = -1
//        notifyDataSetChanged()
//    }
}