package com.example.rickmortymvvm

import android.app.Application
import com.example.rickmortymvvm.di.characterModule
import com.example.rickmortymvvm.di.mainModule
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
