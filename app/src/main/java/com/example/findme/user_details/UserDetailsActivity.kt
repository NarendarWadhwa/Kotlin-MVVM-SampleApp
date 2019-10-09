package com.example.findme.user_details

import android.view.View
import com.example.findme.R
import com.example.findme.base.BaseActivity
import com.example.findme.databinding.ActivityUserDetailsBinding
import com.example.findme.network.response.UserResponse
import com.example.findme.utils.Constants
import com.example.findme.utils.showMessage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : BaseActivity<ActivityUserDetailsBinding, UserDetailsViewModel>(), UserDetailsNavigator,
    OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var user: UserResponse

    override fun getLayoutId() = R.layout.activity_user_details
    override fun getViewModel() = UserDetailsViewModel::class.java
    override fun getNavigator() = this@UserDetailsActivity
    override fun onInternetError() {}
    override fun onError(message: String) = showMessage(message)

    override fun onBinding() {
        supportActionBar?.run {
            title = resources.getString(R.string.user_details_title)
        }
        if (intent != null) {
            intent.extras?.run {
                user = get(Constants.USER_DETAILS) as UserResponse
            }
        }
        (map as SupportMapFragment).getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latLng = LatLng(user.address.location.latitude.toDouble(), user.address.location.latitude.toDouble())
        val address =
            StringBuilder("Name: ").append(user.name).append("\nAddress: ")
                .append(user.address.suite).append(", ")
                .append(user.address.street).append(",\n").append(user.address.city).append(", ")
                .append(user.address.zipCode)
        mBinding.tvAddress.text = address.toString()
        mBinding.cvAddress.visibility = View.VISIBLE

        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_blue_marker))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }


}