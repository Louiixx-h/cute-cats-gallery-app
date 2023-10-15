package com.luishenrique.cutecatsgallery.home.domain

import com.luishenrique.cutecatsgallery.home.domain.model.Image


interface GalleryUseCase {
    suspend fun findAllCats(page: Int) : List<Image>
}
