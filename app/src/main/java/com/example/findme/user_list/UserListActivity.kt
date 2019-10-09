package com.example.findme.user_list

import android.content.Intent
import com.example.findme.R
import com.example.findme.base.BaseActivity
import com.example.findme.base.BaseNavigator
import com.example.findme.databinding.ActivityUserListBinding
import com.example.findme.network.response.UserResponse
import com.example.findme.user_details.UserDetailsActivity
import com.example.findme.utils.Constants
import com.example.findme.utils.showMessage

class UserListActivity : BaseActivity<ActivityUserListBinding, UserListViewModel>(), UserListNavigator {

    override fun getLayoutId() = R.layout.activity_user_list
    override fun getViewModel() = UserListViewModel::class.java
    override fun getNavigator(): BaseNavigator = this@UserListActivity
    override fun onInternetError() {}
    override fun onError(message: String) = showMessage(message)

    override fun onBinding() {
        supportActionBar?.run {
            title = resources.getString(R.string.user_list_title)
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        mBinding.viewPager.adapter = adapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)

    }

    override fun goToUserDetails(user: UserResponse) {
        val userDetailsIntent = Intent(this@UserListActivity, UserDetailsActivity::class.java)
        userDetailsIntent.putExtra(Constants.USER_DETAILS, user)
        startActivity(userDetailsIntent)
    }

}

