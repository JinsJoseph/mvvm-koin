package com.katana.koin

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.katana.koin.di.mvvmModule
import com.utils.Logger
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import java.util.concurrent.TimeUnit

/**
 * Created by Kaz on 10:28 2018-12-19
 */
class MvvmKoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init(true)
        startKoin(this, mvvmModule)

        initFastNetworking()
    }

    /**
     * Init fast networking
     */
    private fun initFastNetworking() {
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
        AndroidNetworking.initialize(this, okHttpClient)
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }
    }
}