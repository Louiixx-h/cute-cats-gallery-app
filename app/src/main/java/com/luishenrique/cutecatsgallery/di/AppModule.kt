package com.luishenrique.cutecatsgallery.di

import com.luishenrique.cutecatsgallery.ui.viewmodel.HomeViewModel
import org.koin.dsl.module

val viewModel = module {
    single {
        HomeViewModel(get())
    }
}