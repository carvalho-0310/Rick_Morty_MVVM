package com.example.rickmortymvvm.app

import android.app.Application
import com.example.rickmortymvvm.BuildConfig
import com.example.rickmortymvvm.app.di.characterModule
import com.example.rickmortymvvm.app.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(this@MyApp)
            modules(mainModule, characterModule)
        }
    }
}
