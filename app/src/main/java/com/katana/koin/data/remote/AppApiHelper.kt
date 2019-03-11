package com.katana.koin.data.remote

import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.model.UserGitHub
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

/**
 * Created by Kaz on 17:32 2019-03-11
 */
class AppApiHelper constructor(private val prefs: PrefsHelper) : ApiHelper {

    override fun getListUser(): Single<List<UserGitHub>> =
            Rx2AndroidNetworking.get(ApiEndPoint.LIST_USER)
                    .build()
                    .getObjectListSingle(UserGitHub::class.java)
}