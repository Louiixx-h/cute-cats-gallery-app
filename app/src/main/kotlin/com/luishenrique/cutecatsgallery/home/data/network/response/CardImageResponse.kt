package com.luishenrique.cutecatsgallery.home.data.network.response

import com.google.gson.annotations.SerializedName

data class CardImageResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("account_url") val username: String?,
    @SerializedName("score") val score: Int?,
    @SerializedName("images") val images: List<CardImageInfoResponse>?
)