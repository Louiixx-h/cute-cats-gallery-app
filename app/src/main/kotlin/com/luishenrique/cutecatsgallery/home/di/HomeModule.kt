package com.luishenrique.cutecatsgallery.home.di

import com.luishenrique.cutecatsgallery.home.data.network.HomeApi
import com.luishenrique.cutecatsgallery.home.data.repository.GalleryRepository
import com.luishenrique.cutecatsgallery.home.data.repository.GalleryRepositoryImpl
import com.luishenrique.cutecatsgallery.home.domain.GalleryUseCase
import com.luishenrique.cutecatsgallery.home.domain.GalleryUseCaseImpl
import com.luishenrique.cutecatsgallery.home.presentation.HomeActivity
import com.luishenrique.cutecatsgallery.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.scopedOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {
    val instance = module {
        scope<HomeActivity> {
            scopedOf(::provideHomeApi)
            factoryOf(::GalleryRepositoryImpl) bind GalleryRepository::class
            factoryOf(::GalleryUseCaseImpl) bind GalleryUseCase::class
            viewModelOf(::HomeViewModel)
        }
    }

    private fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}