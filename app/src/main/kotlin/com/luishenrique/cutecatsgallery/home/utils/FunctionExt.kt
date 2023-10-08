package com.luishenrique.cutecatsgallery.home.utils

import com.luishenrique.cutecatsgallery.home.data.network.response.CardImageResponse
import com.luishenrique.cutecatsgallery.home.data.network.response.GalleryResponse

fun GalleryResponse.filterToPhoto(): List<CardImageResponse> {
    val photosCat: MutableList<CardImageResponse> = mutableListOf()
    var lastName = ""
    data?.forEach { image ->
        image?.images?.forEach {
            if (it.type == "image/jpeg" && image.username != lastName) {
                lastName = image.username.orEmpty()
                photosCat.add(image)
            }
        }
    }
    return photosCat
}

fun GalleryResponse.filterToVideo(): List<CardImageResponse> {
    val videosCat: MutableList<CardImageResponse> = mutableListOf()
    var lastName = ""
    data?.forEach { image ->
        image?.images?.forEach {
            if (it.type == "image/mp4" && image.username != lastName) {
                lastName = image.username.orEmpty()
                videosCat.add(image)
            }
        }
    }
    return videosCat
}