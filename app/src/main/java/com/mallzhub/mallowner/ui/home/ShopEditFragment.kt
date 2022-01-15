package com.mallzhub.mallowner.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.adevinta.leku.LATITUDE
import com.adevinta.leku.LONGITUDE
import com.adevinta.leku.LocationPickerActivity
import com.adevinta.leku.locale.SearchZoneRect
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import com.mallzhub.mallowner.BR
import com.mallzhub.mallowner.R
import com.mallzhub.mallowner.databinding.ShopEditFragmentBinding
import com.mallzhub.mallowner.models.ShoppingMallLevel
import com.mallzhub.mallowner.ui.common.BaseFragment
import com.mallzhub.mallowner.util.showSuccessToast
import java.io.File

class ShopEditFragment : BaseFragment<ShopEditFragmentBinding, ShopEditViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_edit_shop
    override val viewModel: ShopEditViewModel by viewModels {
        viewModelFactory
    }

    val args: ShopEditFragmentArgs by navArgs()

    var malls = arrayOf("Select Mall")
    var levels = arrayOf("Select Level")
    var mallLevels = ArrayList<ShoppingMallLevel>()
    var serviceTypes = arrayOf("Select Service", "Shop", "Location", "Moshjid", "Lift", "Exit", "Toilet", "Fire Exit")

    lateinit var locationResultListener: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbar(viewDataBinding.toolbar)

        val merchant = args.merchant

        viewModel.latitude.postValue(merchant.lat?.toString())
        viewModel.longitude.postValue(merchant.long?.toString())

        locationResultListener = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK || result.data == null ) return@registerForActivityResult
            val latitude = result.data?.getDoubleExtra(LATITUDE, 0.0)
            val longitude = result.data?.getDoubleExtra(LONGITUDE, 0.0)
            viewModel.latitude.postValue(latitude.toString())
            viewModel.longitude.postValue(longitude.toString())
        }

        viewDataBinding.btnFindLocation.setOnClickListener {
            var lat: Double
            var long: Double
            try {
                lat = viewModel.latitude.value?.toDouble() ?: 23.81376
                long = viewModel.longitude.value?.toDouble() ?: 90.42411
            } catch (e: Exception) {
                e.printStackTrace()
                lat = 23.81376
                long = 90.42411
            }
            val locationPickerIntent = LocationPickerActivity.Builder()
                .withLocation(lat, long)
                .withGeolocApiKey("AIzaSyAcDOA5dMt1SMMgMysGT5BczEHRBmqJdyE")
                //.withSearchZone("es_ES")
                //.withSearchZone(SearchZoneRect(LatLng(26.525467, -18.910366), LatLng(43.906271, 5.394197)))
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                //.withStreetHidden()
                //.withCityHidden()
                //.withZipCodeHidden()
                //.withSatelliteViewHidden()
                //.withGooglePlacesEnabled()
                .withGoogleTimeZoneEnabled()
                //.withVoiceSearchHidden()
                //.withUnnamedRoadHidden()
                .build(requireActivity())

            locationResultListener.launch(locationPickerIntent)
        }

        viewDataBinding.toolbar.title = merchant.name

        merchant.shopping_mall?.let {
            val availableMalls = it.name ?: "Unknown Mall"
            malls += availableMalls
        }

        val mallsAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, malls)
        mallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewDataBinding.spinnerMallName.adapter = mallsAdapter

        viewDataBinding.spinnerMallName.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedMallName = if (position != 0) {
                    malls[position]
                } else {
                    ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

        merchant.shopping_mall?.levels?.let {
            it.forEach { level ->
                val levelName = level.name ?: "Unknown Level"
                levels += levelName
                mallLevels.add(level)
            }
        }

        val levelsAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, levels)
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewDataBinding.spinnerMallLevel.adapter = levelsAdapter

        viewDataBinding.spinnerMallLevel.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedLevel = if (position != 0) {
                    mallLevels[position]
                } else {
                    null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

        val serviceTypeAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, serviceTypes)
        serviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewDataBinding.spinnerServiceType.adapter = serviceTypeAdapter

        viewDataBinding.spinnerServiceType.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedServiceType = if (position != 0) {
                    serviceTypes[position]
                } else {
                    ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }

        merchant.shopping_mall?.let {
            val availableMalls = it.name ?: "Unknown Mall"
            malls.forEachIndexed { index, value ->
                if (availableMalls == value) {
                    viewDataBinding.spinnerMallName.setSelection(index)
                }
            }
        }

        merchant.shopping_mall?.levels?.let {
            val level = merchant.shopping_mall_level_id ?: 0
            mallLevels.forEachIndexed { index, value ->
                if (level == value.id) {
                    viewDataBinding.spinnerMallLevel.setSelection(index + 1)
                }
            }
        }

        merchant.type?.let {
            serviceTypes.forEachIndexed { index, value ->
                if (it.equals(value, true)) {
                    viewDataBinding.spinnerServiceType.setSelection(index)
                }
            }
        }

        viewModel.serviceName.postValue(merchant.shop_name)

        viewDataBinding.btnUpdate.setOnClickListener {
            viewModel.updateShop(
                merchant.id,
                merchant.shop_name ?: "",
                merchant.user_name ?: "",
                viewModel.latitude.value ?: "",
                viewModel.longitude.value ?: "",
                merchant.shopping_mall_level_id?.toString() ?: "",
                merchant.shopping_mall_id?.toString() ?: ""
            ).observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    showSuccessToast(requireContext(), "Successfully Updated!")
                    navController.popBackStack()
                }
            })
        }
    }
}