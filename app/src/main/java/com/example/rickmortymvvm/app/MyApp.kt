package com.example.rickmortymvvm.app

import android.app.Application
import com.example.rickmortymvvm.app.di.characterModule
import com.example.rickmortymvvm.app.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(mainModule, characterModule)
        }
    }
}
