package com.katana.koin.ui.splash

import android.os.Bundle
import android.os.Handler
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.ActivitySplashBinding
import com.katana.koin.ui.login.LoginActivity
import com.utils.ext.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun getBindingVariable(): Int = BR.splashModel

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): SplashViewModel = splashViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        splashViewModel.setNavigator(this)

        Handler().postDelayed({
//            if (splashViewModel.getUserInfo() != null) {
//                startActivity(MainActivity::class.java)
//            } else {
                startActivity(LoginActivity::class.java)
//            }
//            finish()
        }, 500)
    }
}

