package com.rtchubs.talika.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rtchubs.talika.BR
import com.rtchubs.talika.R
import com.rtchubs.talika.databinding.Home2Binding
import com.rtchubs.talika.models.ShoppingMall
import com.rtchubs.talika.models.ShoppingMallRequestBody
import com.rtchubs.talika.ui.LogoutHandlerCallback
import com.rtchubs.talika.ui.NavDrawerHandlerCallback
import com.rtchubs.talika.ui.common.BaseFragment
import com.rtchubs.talika.ui.login.SliderView
import com.rtchubs.talika.ui.mall_iot.MallIOTFragmentFragment

class Home2Fragment : BaseFragment<Home2Binding, HomeViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_main2
    override val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    lateinit var paymentListAdapter: PaymentMethodListAdapter

    private var listener: LogoutHandlerCallback? = null

    private var drawerListener: NavDrawerHandlerCallback? = null

    private lateinit var mallsListAdapter: MallsListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LogoutHandlerCallback) {
            listener = context
        } else {
            throw RuntimeException("$context must implement LoginHandlerCallback")
        }

        if (context is NavDrawerHandlerCallback) {
            drawerListener = context
        } else {
            throw RuntimeException("$context must implement LoginHandlerCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        drawerListener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mallsListAdapter = MallsListAdapter(appExecutors) { mall ->
            viewModel.shoppingMallResponse.value?.data?.let {
                navigateTo(Home2FragmentDirections.actionHome2FragmentToAllShopListFragment(it))
            }
        }
        viewDataBinding.recyclerMall.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        viewDataBinding.recyclerMall.adapter = mallsListAdapter

        viewDataBinding.btnNewChat.setOnClickListener {
            navigateTo(Home2FragmentDirections.actionHome2FragmentToBotNav())
        }

        viewDataBinding.appLogo.setOnClickListener {
            drawerListener?.toggleNavDrawer()
        }

        viewModel.slideDataList.forEach { slideData ->
            val slide = SliderView(requireContext())
            slide.sliderTextTitle = slideData.textTitle
            slide.sliderTextDescription = slideData.descText
            slide.sliderImage(slideData.slideImage)
            viewDataBinding.sliderLayout.addSlider(slide)
        }

        viewModel.shoppingMallResponse.observe(viewLifecycleOwner, { response ->
            val malls = ArrayList<ShoppingMall>()
            MallIOTFragmentFragment.shoppingMall = response?.data
            response?.data?.mall?.let { mall ->
                malls.add(mall)
            }
            mallsListAdapter.submitList(malls)
            visibleGoneEmptyView(malls)
        })

        viewModel.getOwnerMalls(ShoppingMallRequestBody(preferencesHelper.getMallOwner().email, "notshop"))
    }

    private fun visibleGoneEmptyView(malls: List<ShoppingMall>) {
        if (malls.isEmpty()) {
            viewDataBinding.recyclerMall.visibility = View.GONE
            viewDataBinding.emptyView.visibility = View.VISIBLE
        } else {
            viewDataBinding.recyclerMall.visibility = View.VISIBLE
            viewDataBinding.emptyView.visibility = View.GONE
        }
    }
}