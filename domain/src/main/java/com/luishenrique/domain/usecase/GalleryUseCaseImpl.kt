package com.luishenrique.domain.usecase

import com.luishenrique.domain.entity.Gallery

class GalleryUseCaseImpl(private val repository: GalleryUseCase): GalleryUseCase {

    override suspend fun findAllCats(): Gallery {
        return repository.findAllCats()
    }
}
