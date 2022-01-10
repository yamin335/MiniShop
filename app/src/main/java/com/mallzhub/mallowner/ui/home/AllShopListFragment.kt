package com.mallzhub.mallowner.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.AllShopListFragmentBinding
import com.mallzhub.mallowner.models.LevelWiseShops
import com.mallzhub.mallowner.models.MallMerchant
import com.mallzhub.mallowner.models.Merchant
import com.mallzhub.mallowner.models.ShoppingMallLevel
import com.mallzhub.mallowner.ui.common.BaseFragment

class AllShopListFragment :
    BaseFragment<AllShopListFragmentBinding, AllShopListViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_all_shop_list
    override val viewModel: AllShopListViewModel by viewModels {
        viewModelFactory
    }

    val args: AllShopListFragmentArgs by navArgs()

    lateinit var allShopListAdapter: AllShopListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbar(viewDataBinding.toolbar)

        viewDataBinding.toolbar.title = args.shoppingMall.mall?.name

        allShopListAdapter = AllShopListAdapter(
            appExecutors, actionCallBack = object : ShopListAdapter.ShopListActionCallback {
                override fun edit(merchant: MallMerchant) {
                    navigateTo(AllShopListFragmentDirections.actionAllShopListFragmentToShopEditFragment(merchant))
                }

                override fun deActivate(merchant: MallMerchant) {

                }

            }) {

        }

        viewDataBinding.rvAllShopList.adapter = allShopListAdapter

        val shopList = args.shoppingMall.marchents?.data ?: return

        if (shopList.isEmpty()) return

        val mallId = args.shoppingMall.mall?.id ?: -1
        val shops = ArrayList<MallMerchant>()
        shopList.forEach { merchant ->
            if (merchant.shopping_mall_id == mallId)
                shops.add(merchant)
        }

        if (shops.isEmpty()) return

        var levels = ArrayList<ShoppingMallLevel>()
        val levelsMap = HashMap<Int, ArrayList<MallMerchant>>()
        shops.forEach { merchant ->
            val id = merchant.shopping_mall_level_id ?: -1
            if (levelsMap.containsKey(id)) {
                val arrayList = levelsMap[id]
                arrayList?.add(merchant)
            } else if (id != -1) {
                val arrayList = ArrayList<MallMerchant>()
                arrayList.add(merchant)
                levelsMap[id] = arrayList
                val lvls = merchant.shopping_mall?.levels
                if (levels.isEmpty() && !lvls.isNullOrEmpty()) {
                    levels = lvls as ArrayList<ShoppingMallLevel>
                }
            }
        }

        if (shops.isEmpty() || levels.isEmpty()) return

        val lvlMap = HashMap<Int, ShoppingMallLevel>()
        levels.forEach { level ->
            val id = level.id
            if (id != null && levelsMap.containsKey(id)) {
                lvlMap[id] = level
            }
        }

        val levelWiseShops = ArrayList<LevelWiseShops>()
        val keys = levelsMap.keys.sorted()
        keys.forEach { key ->
            levelWiseShops.add(LevelWiseShops(lvlMap[key], levelsMap[key]))
        }
        allShopListAdapter.submitList(levelWiseShops)
    }
}