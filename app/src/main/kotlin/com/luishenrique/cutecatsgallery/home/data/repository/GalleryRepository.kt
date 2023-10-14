package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.cutecatsgallery.home.domain.model.Gallery

interface GalleryRepository {
    suspend fun findAllCats(page: Int): Gallery
}