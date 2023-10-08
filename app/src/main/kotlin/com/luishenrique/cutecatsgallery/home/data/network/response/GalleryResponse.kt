package com.luishenrique.cutecatsgallery.home.data.network.response

import com.google.gson.annotations.SerializedName

class Gallery(
    @SerializedName("data") val data: List<Image>
) {
    fun filterToPhoto(): List<Image> {
        val photosCat: MutableList<Image> = mutableListOf()
        var lastName = ""
        var currentName = ""
        data.forEach { image ->
            currentName = image.username
            image.images?.forEach {
                if (it.type == "image/jpeg" && currentName != lastName) {
                    lastName = currentName
                    photosCat.add(image)
                }
            }
        }
        return photosCat
    }

    fun filterToVideo(): List<Image> {
        val videosCat: MutableList<Image> = mutableListOf()
        var lastName = ""
        var currentName = ""
        data.forEach { image ->
            currentName = image.username
            image.images?.forEach {
                if (it.type == "image/mp4" && currentName != lastName) {
                    lastName = currentName
                    videosCat.add(image)
                }
            }
        }
        return videosCat
    }
}

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