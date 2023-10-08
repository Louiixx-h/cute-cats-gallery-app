package com.luishenrique.cutecatsgallery.home.data.network.response

import com.google.gson.annotations.SerializedName

data class CardImageInfoResponse(
    @SerializedName("type") val type: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("gifv") val gifv: String?,
    @SerializedName("mp4") val mp4: String?,
)