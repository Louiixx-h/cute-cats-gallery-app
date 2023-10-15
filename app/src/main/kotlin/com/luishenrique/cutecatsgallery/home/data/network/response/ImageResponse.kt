package com.luishenrique.cutecatsgallery.home.data.network.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("url") val imageURL: String?
)