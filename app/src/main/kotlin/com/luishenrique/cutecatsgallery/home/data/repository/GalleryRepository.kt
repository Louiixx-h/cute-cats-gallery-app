package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.cutecatsgallery.home.domain.model.Image

interface GalleryRepository {
    suspend fun findAllCats(page: Int): List<Image>
}