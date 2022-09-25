package com.rtchubs.talika.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.AllShopListFragmentBinding
import com.rtchubs.talika.models.*
import com.rtchubs.talika.ui.common.BaseFragment

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
    lateinit var allOtherServiceListAdapter: AllShopListAdapter

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
            navigateTo(AllShopListFragmentDirections.actionAllShopListFragmentToShopDetailFragment(it))
        }

        allOtherServiceListAdapter = AllShopListAdapter(
            appExecutors, actionCallBack = object : ShopListAdapter.ShopListActionCallback {
                override fun edit(merchant: MallMerchant) {
                    navigateTo(AllShopListFragmentDirections.actionAllShopListFragmentToShopEditFragment(merchant))
                }

                override fun deActivate(merchant: MallMerchant) {

                }

            }) {
            navigateTo(AllShopListFragmentDirections.actionAllShopListFragmentToShopDetailFragment(it))
        }

        viewDataBinding.recyclerShop.adapter = allShopListAdapter
        viewDataBinding.recyclerOthers.adapter = allOtherServiceListAdapter

        viewDataBinding.btnServiceType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            // Respond to button selection
            if (isChecked) {
                when (checkedId) {
                    R.id.shop -> {
                        viewModel.checkedServiceType = 0
                        viewModel.getOwnerMallAllMerchants(
                            ShoppingMallRequestBody(
                                preferencesHelper.getMallOwner().email,
                                "shop"
                            )
                        )
                    }
                    R.id.others -> {
                        viewModel.checkedServiceType = 1
                        viewModel.getOwnerMalls(
                            ShoppingMallRequestBody(
                                preferencesHelper.getMallOwner().email,
                                "notshop"
                            )
                        )
                    }
                }
            }
        }

        when (viewModel.checkedServiceType) {
            0 -> viewDataBinding.btnServiceType.check(R.id.shop)
            1 -> viewDataBinding.btnServiceType.check(R.id.others)
        }

        viewModel.shoppingMallResponse.observe(viewLifecycleOwner, { response ->
            visibleGoneEmptyView(response)
            val shopList = response ?: return@observe

            if (shopList.isEmpty()) return@observe

            val mallId = args.shoppingMall.mall?.id ?: -1
            val shops = ArrayList<MallMerchant>()
            shopList.forEach { merchant ->
                if (merchant.shopping_mall_id == mallId)
                    shops.add(merchant)
            }

            if (shops.isEmpty()) return@observe

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

            if (shops.isEmpty() || levels.isEmpty()) return@observe

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

            when (viewModel.checkedServiceType) {
                0 -> {
                    allShopListAdapter.submitList(levelWiseShops)
                }
                1 -> {
                    allOtherServiceListAdapter.submitList(levelWiseShops)
                }
            }
        })
    }

    private fun visibleGoneEmptyView(malls: List<MallMerchant>?) {
        if (malls.isNullOrEmpty()) {
            viewDataBinding.dataView.visibility = View.GONE
            viewDataBinding.emptyView.visibility = View.VISIBLE
        } else {
            viewDataBinding.dataView.visibility = View.VISIBLE
            viewDataBinding.emptyView.visibility = View.GONE
            when (viewModel.checkedServiceType) {
                0 -> {
                    viewDataBinding.recyclerOthers.visibility = View.GONE
                    viewDataBinding.recyclerShop.visibility = View.VISIBLE
                }
                1 -> {
                    viewDataBinding.recyclerShop.visibility = View.GONE
                    viewDataBinding.recyclerOthers.visibility = View.VISIBLE
                }
            }
        }
    }
}