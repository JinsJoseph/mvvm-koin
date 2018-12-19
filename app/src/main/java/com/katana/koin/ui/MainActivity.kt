package com.katana.koin.ui

import android.os.Bundle
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.ActivityMainBinding
import com.katana.koin.ui.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.mainModel

    override fun updateUI(savedInstanceState: Bundle?) {
        mainViewModel.setNavigator(this)
        mainViewModel.saveUser("f")
//        toast(mainViewModel.getUser()!!)

        openFragment(R.id.content_home, HomeFragment::class.java, null, false)
    }

    override fun getViewModel(): MainViewModel = mainViewModel
}
