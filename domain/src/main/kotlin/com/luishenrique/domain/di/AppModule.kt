package com.luishenrique.domain.di

import com.luishenrique.domain.ApiService
import com.luishenrique.domain.repository.GalleryRepository
import com.luishenrique.domain.repository.GalleryRepositoryImpl
import com.luishenrique.domain.usecase.GalleryUseCase
import com.luishenrique.domain.usecase.GalleryUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.imgur.com/3/"

val apiServiceModule = module {
 single<Retrofit> {
  Retrofit.Builder()
   .baseUrl(BASE_URL)
   .addConverterFactory(GsonConverterFactory.create())
   .build()
 }

 single<ApiService> {
  get<Retrofit>().create(ApiService::class.java)
 }
}

val repositoryModule = module {
 factory<GalleryRepository> {
  GalleryRepositoryImpl(apiService = get())
 } bind GalleryRepository::class
}

val useCaseModule = module {
 factory<GalleryUseCase> {
  GalleryUseCaseImpl(repository = get())
 } bind GalleryUseCase::class
}