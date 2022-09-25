package com.rtchubs.talika.ui.mall_iot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.MallIOTFragmentBinding
import com.rtchubs.talika.models.*
import com.rtchubs.talika.ui.common.BaseFragment
import com.rtchubs.talika.ui.home.AllShopListAdapter
import com.rtchubs.talika.ui.home.ShopListAdapter

class MallIOTFragmentFragment : BaseFragment<MallIOTFragmentBinding, MallIOTViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_mall_iot
    override val viewModel: MallIOTViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var mallIOTDevices: AllShopListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbar(viewDataBinding.toolbar)

        viewDataBinding.toolbar.title = shoppingMall?.mall?.name ?: "Mall Devices"

        mallIOTDevices = AllShopListAdapter(
            appExecutors, actionCallBack = object : ShopListAdapter.ShopListActionCallback {
                override fun edit(merchant: MallMerchant) {
                    //navigateTo(AllShopListFragmentDirections.actionAllShopListFragmentToShopEditFragment(merchant))
                }

                override fun deActivate(merchant: MallMerchant) {

                }

            }) {
            navigateTo(MallIOTFragmentFragmentDirections.actionMallIOTFragmentFragmentToMyDevicesFragment())
        }

        viewDataBinding.recyclerDevices.adapter = mallIOTDevices

        viewModel.shoppingMallResponse.observe(viewLifecycleOwner, { response ->
            visibleGoneEmptyView(response)
            val shopList = response ?: return@observe

            if (shopList.isEmpty()) return@observe

            val mallId = shoppingMall?.mall?.id ?: -1
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
            mallIOTDevices.submitList(levelWiseShops)
        })
        viewModel.getOwnerMalls(
            ShoppingMallRequestBody(
                preferencesHelper.getMallOwner().email,
                "notshop"
            )
        )
    }

    private fun visibleGoneEmptyView(malls: List<MallMerchant>?) {
        if (malls.isNullOrEmpty()) {
            viewDataBinding.recyclerDevices.visibility = View.GONE
            viewDataBinding.emptyView.visibility = View.VISIBLE
        } else {
            viewDataBinding.recyclerDevices.visibility = View.VISIBLE
            viewDataBinding.emptyView.visibility = View.GONE
        }
    }

    companion object {
        var shoppingMall: ShoppingMallResponseData? = null
    }
}