package com.luishenrique.domain.repository

import com.luishenrique.domain.entity.Gallery

interface GalleryRepository {
    suspend fun findAllCats(): Gallery?
}