package com.luishenrique.domain.entity

import com.google.gson.annotations.SerializedName

class Gallery(
    @SerializedName("data") val data: List<Image>
)

class Image(
    @SerializedName("title") val title :String,
    @SerializedName("account_url") val username :String,
    @SerializedName("score") val score :String,
    @SerializedName("link") val image :String
)