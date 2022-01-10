package com.mallzhub.mallowner.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.mallzhub.mallowner.AppExecutors
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.ShopListItemBinding
import com.mallzhub.mallowner.models.MallMerchant
import com.mallzhub.mallowner.models.Merchant

import com.mallzhub.mallowner.models.PaymentMethod
import com.mallzhub.mallowner.models.Product
import com.mallzhub.mallowner.util.DataBoundListAdapter
import kotlinx.android.synthetic.main.popup_menu_product_item.view.*

class ShopListAdapter(
    private val appExecutors: AppExecutors,
    private val actionCallBack: ShopListActionCallback,
    private val itemCallback: ((MallMerchant) -> Unit)? = null
) : DataBoundListAdapter<MallMerchant, ShopListItemBinding>(
    appExecutors = appExecutors, diffCallback = object : DiffUtil.ItemCallback<MallMerchant>() {
        override fun areItemsTheSame(oldItem: MallMerchant, newItem: MallMerchant): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MallMerchant,
            newItem: MallMerchant
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup): ShopListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_shop, parent, false
        )
    }


    override fun bind(binding: ShopListItemBinding, position: Int) {
        val item = getItem(position)
        binding.merchant = item

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
        val context = binding.root.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewPopupMenu = inflater.inflate(R.layout.shop_list_action_menu, null)
        val popupMenu = PopupWindow(viewPopupMenu, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupMenu.isOutsideTouchable = true
        //popupMenu.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        popupMenu.animationStyle = android.R.style.Animation_Dialog
        popupMenu.elevation = 20F

        popupMenu.setOnDismissListener {
            //        Toast.makeText(context, "Dismissed!!!", Toast.LENGTH_LONG).show()
        }

        val popupMenuView = popupMenu.contentView
        val menuEdit: MaterialCardView = popupMenuView.findViewById(R.id.menuEdit)
        val menuInactive: MaterialCardView = popupMenuView.findViewById(R.id.menuInactive)
        menuEdit.setOnClickListener {
            actionCallBack.edit(item)
            popupMenu.dismiss()
        }

        menuInactive.setOnClickListener {
            actionCallBack.deActivate(item)
            popupMenu.dismiss()
        }

        binding.menu.setOnClickListener {
            popupMenu.showAsDropDown(binding.menu,-200, -150, Gravity.NO_GRAVITY)
        }
    }

    interface ShopListActionCallback {
        fun edit(merchant: MallMerchant)
        fun deActivate(merchant: MallMerchant)
    }
}