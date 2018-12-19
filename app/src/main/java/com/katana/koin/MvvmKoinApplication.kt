package com.katana.koin

import android.app.Application
import com.katana.koin.di.mvvmModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Kaz on 10:28 2018-12-19
 */
class MvvmKoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, mvvmModule)
    }
}