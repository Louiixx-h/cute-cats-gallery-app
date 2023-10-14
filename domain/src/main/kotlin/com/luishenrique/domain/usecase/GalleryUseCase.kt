package com.luishenrique.domain.usecase

import com.luishenrique.domain.entity.Gallery

interface GalleryUseCase {
    fun findAllCats(page: Int, onSuccess: suspend (Gallery) -> Unit, onError: suspend () -> Unit)
}
