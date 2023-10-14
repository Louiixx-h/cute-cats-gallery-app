package com.luishenrique.cutecatsgallery.home.domain

import com.luishenrique.cutecatsgallery.home.domain.model.Gallery


interface GalleryUseCase {
    suspend fun findAllCats(page: Int) : Gallery
}
