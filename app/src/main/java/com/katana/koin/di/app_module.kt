package com.katana.koin.di

import com.google.gson.GsonBuilder
import com.katana.koin.data.AppDataManager
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.prefs.AppPrefsHelper
import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.ui.MainViewModel
import com.katana.koin.ui.home.HomeViewModel
import com.katana.koin.ui.other.OtherHihi
import com.utils.SchedulerProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Created by Kaz on 10:16 2018-12-19
 */
//define app module gson, data manager, etc...
val appModule: Module = module {

    single { SchedulerProvider() }

    single { AppPrefsHelper(get(), "Katana", get()) as PrefsHelper }

    single { AppDataManager(get()) as DataManager }

    single { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()!! }

    factory { OtherHihi(get(), get()) }
}

//define list view model
val viewModule = module {
    viewModel { MainViewModel(get(), get(), get()) }

    viewModel { HomeViewModel(get(), get()) }
}

val otherModule = module {
//    provide { OtherHihi() }
}

val mvvmModule = listOf(appModule, viewModule)