package com.katana.koin.di

import com.google.gson.GsonBuilder
import com.katana.koin.data.AppDataManager
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.prefs.AppPrefsHelper
import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.remote.ApiHelper
import com.katana.koin.data.remote.AppApiHelper
import com.utils.SchedulerProvider
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Created by Kaz on 10:16 2018-12-19
 */
//define app module gson, data manager, etc...
val appModule: Module = module {

    single { SchedulerProvider() }

    single { AppPrefsHelper(get(), "Katana", get()) as PrefsHelper }

    single { AppDataManager(get(), get()) as DataManager }

    single { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!! }

    single { AppApiHelper(get()) as ApiHelper }
}

val mvvmModule = listOf(appModule, viewModule)