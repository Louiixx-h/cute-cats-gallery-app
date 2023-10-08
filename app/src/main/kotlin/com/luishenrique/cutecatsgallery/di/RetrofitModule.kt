package com.luishenrique.cutecatsgallery.di

import com.luishenrique.cutecatsgallery.BuildConfig
import com.luishenrique.cutecatsgallery.network.CustomInterceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    val instance = module {
        factoryOf(::CustomInterceptor)
        factoryOf(::provideOkHttpClient)
        singleOf(::provideRetrofit)
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideOkHttpClient(interceptor: CustomInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }
}