package com.luishenrique.cutecatsgallery.home.data.mapper

import com.luishenrique.cutecatsgallery.home.data.network.response.CardImageInfoResponse
import com.luishenrique.cutecatsgallery.home.data.network.response.CardImageResponse
import com.luishenrique.cutecatsgallery.home.data.network.response.GalleryResponse
import com.luishenrique.cutecatsgallery.home.domain.model.CardImage
import com.luishenrique.cutecatsgallery.home.domain.model.CardImageInfo
import com.luishenrique.cutecatsgallery.home.domain.model.Gallery

object GalleryMapper {

    fun mapToModel(response: GalleryResponse) : Gallery {
        return Gallery(
            data = getCards(response.data)
        )
    }

    private fun getCards(data: List<CardImageResponse?>?): List<CardImage> {
        return data?.map { getCardImage(it) }.orEmpty()
    }

    private fun getCardImage(cardImage: CardImageResponse?) : CardImage {
        return CardImage(
            title = cardImage?.title.orEmpty(),
            username = cardImage?.username.orEmpty(),
            score = cardImage?.score ?: 0,
            images = getImages(cardImage?.images).orEmpty()
        )
    }

    private fun getImages(images: List<CardImageInfoResponse>?): List<CardImageInfo>? {
        return images?.map { getCardImageInfo(it) }
    }

    private fun getCardImageInfo(cardImageInfo: CardImageInfoResponse?) : CardImageInfo {
        return CardImageInfo(
            type = cardImageInfo?.type.orEmpty(),
            link = cardImageInfo?.link.orEmpty(),
            gifv = cardImageInfo?.gifv.orEmpty(),
            mp4 = cardImageInfo?.mp4.orEmpty()
        )
    }
}