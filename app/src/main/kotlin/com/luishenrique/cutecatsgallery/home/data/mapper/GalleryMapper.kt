package com.luishenrique.cutecatsgallery.home.data.mapper

import com.luishenrique.cutecatsgallery.base.BaseMapper
import com.luishenrique.cutecatsgallery.home.data.network.response.ImageResponse
import com.luishenrique.cutecatsgallery.home.domain.model.Image

class GalleryMapper : BaseMapper<List<ImageResponse>, List<Image>> {

    override fun toUiModel(response: List<ImageResponse>?): List<Image> {
        return response?.map { Image(id = it.id, imageURL = it.imageURL) }.orEmpty()
    }

    override fun toResponse(uiModel: List<Image>?): List<ImageResponse> {
        return uiModel?.map { ImageResponse(id = it.id, imageURL = it.imageURL) }.orEmpty()
    }
}