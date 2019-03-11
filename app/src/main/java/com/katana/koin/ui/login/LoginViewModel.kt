package com.katana.koin.ui.login

import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

/**
 * Created by Kaz on 15:25 9/20/18
 */
class LoginViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) : BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    fun login() = getNavigator().login()
}