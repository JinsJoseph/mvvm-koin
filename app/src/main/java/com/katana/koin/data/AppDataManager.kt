package com.katana.koin.data

import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.model.UserGitHub
import com.katana.koin.data.remote.ApiHelper
import io.reactivex.Single

/**
 * Created by Kaz on 10:14 2018-12-19
 */
class AppDataManager constructor(private val prefsHelper: PrefsHelper,
                                 private val apiHelper: ApiHelper) : DataManager {

    override fun getUser(): String? = prefsHelper.getUser()

    override fun saveUser(user: String) = prefsHelper.saveUser(user)

    override fun getListUser(): Single<List<UserGitHub>> = apiHelper.getListUser()
}