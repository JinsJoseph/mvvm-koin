package com.katana.koin.ui.home

import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

/**
 * Created by Kaz on 10:53 2018-12-19
 */
class HomeViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) : BaseViewModel<HomeNavigator>(dataManager, schedulerProvider) {

    fun getListUser() {
        launch {
            dataManager.getListUser()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({
                        // get list user success
                    }, {
                        // get list user failed
                        it.printStackTrace()
                    })
        }
    }
}