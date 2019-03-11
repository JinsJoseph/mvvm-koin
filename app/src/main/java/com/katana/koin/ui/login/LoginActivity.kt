package com.katana.koin.ui.login

import android.os.Bundle
import android.os.Handler
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.ActivityLoginBinding
import com.katana.koin.ui.MainActivity
import com.utils.ext.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun getBindingVariable(): Int = BR.loginModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): LoginViewModel = loginViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        loginViewModel.setNavigator(this)
    }

    override fun login() {
        showDialog()
        Handler().postDelayed({
            hideDialog()
            startActivity(MainActivity::class.java)
        }, 400)
    }
}
