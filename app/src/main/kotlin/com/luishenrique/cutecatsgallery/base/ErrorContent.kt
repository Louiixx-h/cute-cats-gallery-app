package com.luishenrique.cutecatsgallery.base

data class ErrorContent(
    val message: String,
    val throwable: Throwable
)