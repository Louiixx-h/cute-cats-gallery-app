package com.luishenrique.cutecatsgallery

import android.app.Application
import com.luishenrique.cutecatsgallery.di.DependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(DependencyInjection.modules)
        }
    }
}