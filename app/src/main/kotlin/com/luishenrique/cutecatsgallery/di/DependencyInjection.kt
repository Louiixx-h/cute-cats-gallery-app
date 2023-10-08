package com.luishenrique.cutecatsgallery.di

import com.luishenrique.cutecatsgallery.home.di.HomeModule

object DependencyInjection {
    val modules = listOf(
        HomeModule.instance
    )
}