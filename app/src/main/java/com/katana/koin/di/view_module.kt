package com.katana.koin.di

import com.katana.koin.ui.MainViewModel
import com.katana.koin.ui.home.HomeViewModel
import com.katana.koin.ui.login.LoginViewModel
import com.katana.koin.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Kaz on 17:33 2019-03-11
 */

//define list view model
val viewModule = module {
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SplashViewModel(get(), get()) }
}
