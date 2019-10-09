package com.example.findme.splash

import android.content.Intent
import android.os.Handler
import android.view.Window
import com.example.findme.R
import com.example.findme.base.BaseActivity
import com.example.findme.base.BaseNavigator
import com.example.findme.databinding.ActivitySplashBinding
import com.example.findme.user_list.UserListActivity
import com.example.findme.utils.showMessage

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val handler = Handler()

    override fun getLayoutId(): Int {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        return R.layout.activity_splash
    }

    override fun getViewModel(): Class<SplashViewModel> = SplashViewModel::class.java
    override fun getNavigator(): BaseNavigator = this@SplashActivity
    override fun onInternetError() {}
    override fun onError(message: String) = showMessage(message)

    override fun onBinding() {
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, UserListActivity::class.java))
            finish()
        }, 2000)
    }

}
