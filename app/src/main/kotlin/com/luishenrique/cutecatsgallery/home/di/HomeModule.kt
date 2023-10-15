package com.luishenrique.cutecatsgallery.home.di

import com.luishenrique.cutecatsgallery.home.data.dataSource.GalleryDataSource
import com.luishenrique.cutecatsgallery.home.data.dataSource.GalleryDataSourceImpl
import com.luishenrique.cutecatsgallery.home.data.mapper.GalleryMapper
import com.luishenrique.cutecatsgallery.home.data.network.HomeApiService
import com.luishenrique.cutecatsgallery.home.data.repository.GalleryRepository
import com.luishenrique.cutecatsgallery.home.data.repository.GalleryRepositoryImpl
import com.luishenrique.cutecatsgallery.home.domain.GalleryUseCase
import com.luishenrique.cutecatsgallery.home.domain.GalleryUseCaseImpl
import com.luishenrique.cutecatsgallery.home.presentation.HomeViewModel
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {
    val instance = module {
        singleOf(::provideHomeApi)
        factoryOf(::GalleryMapper)
        factoryOf(::GalleryDataSourceImpl) bind GalleryDataSource::class
        factoryOf(::GalleryRepositoryImpl) bind GalleryRepository::class
        factoryOf(::GalleryUseCaseImpl) bind GalleryUseCase::class
        viewModel { HomeViewModel(get(), Job()) }
    }

    private fun provideHomeApi(retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }
}