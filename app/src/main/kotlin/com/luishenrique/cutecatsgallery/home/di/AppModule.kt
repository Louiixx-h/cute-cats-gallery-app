package com.luishenrique.cutecatsgallery.home.di

import com.luishenrique.cutecatsgallery.home.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(useCase = get()) }
}