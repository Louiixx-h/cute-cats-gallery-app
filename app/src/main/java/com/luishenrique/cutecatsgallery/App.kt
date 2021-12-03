package com.luishenrique.cutecatsgallery

import android.app.Application
import com.luishenrique.cutecatsgallery.di.viewModelModule
import com.luishenrique.domain.di.apiServiceModule
import com.luishenrique.domain.di.repositoryModule
import com.luishenrique.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    apiServiceModule,
                    repositoryModule,
                    useCaseModule,
                )
            )
        }
    }
}