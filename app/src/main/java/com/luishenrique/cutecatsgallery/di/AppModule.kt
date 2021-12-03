package com.luishenrique.cutecatsgallery.di

import com.luishenrique.cutecatsgallery.ui.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(useCase = get()) }
}