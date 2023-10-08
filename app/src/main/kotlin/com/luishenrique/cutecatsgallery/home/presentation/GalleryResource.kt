package com.luishenrique.cutecatsgallery.home.presentation

sealed class GalleryResource<out R> {
    data class Success<out T>(val data: T?) : GalleryResource<T?>()
    data class Error(val exception: Exception) : GalleryResource<Nothing>()
}