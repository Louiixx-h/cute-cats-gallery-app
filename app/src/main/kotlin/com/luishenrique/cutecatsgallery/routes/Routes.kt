package com.luishenrique.cutecatsgallery.routes

sealed class Routes(val route: String) {
    object Home : Routes("HOME")
    object Details : Routes("DETAILS")
}
