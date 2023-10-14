package com.luishenrique.cutecatsgallery.home.data.network.response

import com.google.gson.annotations.SerializedName

data class GalleryResponse(
    @SerializedName("data") val data: List<CardImageResponse?>?
)