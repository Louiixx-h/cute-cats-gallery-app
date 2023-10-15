package com.luishenrique.cutecatsgallery.home.domain

import com.luishenrique.cutecatsgallery.home.data.repository.GalleryRepository
import com.luishenrique.cutecatsgallery.home.domain.model.Image

class GalleryUseCaseImpl(private val repository: GalleryRepository) : GalleryUseCase {

    override suspend fun findAllCats(page: Int) : List<Image> {
        return repository.findAllCats(page)
    }
}
