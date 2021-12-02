package com.luishenrique.domain.usecase

import com.luishenrique.domain.entity.Gallery

interface GalleryUseCase {
    suspend fun findAllCats(): Gallery
}
