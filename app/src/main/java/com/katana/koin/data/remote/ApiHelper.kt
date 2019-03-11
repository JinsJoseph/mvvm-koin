package com.katana.koin.data.remote

import com.katana.koin.data.model.UserGitHub
import io.reactivex.Single

/**
 * Created by Kaz on 17:32 2019-03-11
 */
interface ApiHelper {

    fun getListUser(): Single<List<UserGitHub>>
}