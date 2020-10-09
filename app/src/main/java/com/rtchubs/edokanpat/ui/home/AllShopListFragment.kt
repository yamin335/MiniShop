package com.rtchubs.edokanpat.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.rtchubs.edokanpat.BR
import com.rtchubs.edokanpat.R
import com.rtchubs.edokanpat.databinding.AllShopListFragmentBinding
import com.rtchubs.edokanpat.databinding.MoreShoppingListFragmentBinding
import com.rtchubs.edokanpat.models.LevelWiseShops
import com.rtchubs.edokanpat.models.Merchant
import com.rtchubs.edokanpat.models.ShoppingMallLevel
import com.rtchubs.edokanpat.ui.common.BaseFragment
import com.rtchubs.edokanpat.ui.otp_signin.OtpSignInFragmentArgs
import com.rtchubs.edokanpat.util.GridRecyclerItemDecorator

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

        viewDataBinding.toolbar.title = args.shoppingMall.name

        allShopListAdapter = AllShopListAdapter(
                appExecutors
            ) { item ->
            navController.navigate(AllShopListFragmentDirections.actionAllShopListFragmentToProductListFragment(item))
        }

        viewDataBinding.rvAllShopList.adapter = allShopListAdapter

        viewModel.allShopListResponse.observe(viewLifecycleOwner, Observer { response ->
            response?.data?.let { shopList ->
                if (shopList.isEmpty())
                    return@Observer

                val mallId = args.shoppingMall.id ?: -1
                val shops = ArrayList<Merchant>()
                shopList.forEach { merchant ->
                    if (merchant.shopping_mall_id == mallId)
                        shops.add(merchant)
                }

                if (shops.isEmpty())
                    return@Observer

                var levels = ArrayList<ShoppingMallLevel>()
                val levelsMap = HashMap<Int, ArrayList<Merchant>>()
                shops.forEach { merchant ->
                    val id = merchant.shopping_mall_level_id ?: -1
                    if (levelsMap.containsKey(id)) {
                        val arrayList = levelsMap[id]
                        arrayList?.add(merchant)
                    } else if (id != -1) {
                        val arrayList = ArrayList<Merchant>()
                        arrayList.add(merchant)
                        levelsMap[id] = arrayList
                        val lvls = merchant.shopping_mall?.levels
                        if (levels.isEmpty() && !lvls.isNullOrEmpty()) {
                            levels = lvls as ArrayList<ShoppingMallLevel>
                        }
                    }
                }

                if (shops.isEmpty() || levels.isEmpty())
                    return@Observer

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
        })

        viewModel.getAllShopList()
    }
}