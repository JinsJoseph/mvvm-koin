package com.katana.koin.ui.splash

import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

/**
 * Created by Kaz on 17:21 2019-03-11
 */
class SplashViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) : BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    fun getUserInfo() = dataManager.getUser()
}