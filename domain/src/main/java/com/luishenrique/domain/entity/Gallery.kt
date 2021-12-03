package com.luishenrique.domain.entity

import com.google.gson.annotations.SerializedName

class Gallery(
    @SerializedName("data") val data: List<Image>
)

class Image(
    @SerializedName("title") val title: String,
    @SerializedName("account_url") val username: String,
    @SerializedName("score") val score: Int,
    @SerializedName("images") val images: List<ImageInfo>?
)

class ImageInfo(
    @SerializedName("type") val type: String,
    @SerializedName("link") val link: String,
    @SerializedName("gifv") val gifv: String,
    @SerializedName("mp4") val mp4: String,
)