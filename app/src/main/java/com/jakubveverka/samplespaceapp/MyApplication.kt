package com.jakubveverka.samplespaceapp

import android.app.Application
import com.jakubveverka.samplespaceapp.di.appModule
import com.jakubveverka.spacedata.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, dataModule)
        }
    }
}