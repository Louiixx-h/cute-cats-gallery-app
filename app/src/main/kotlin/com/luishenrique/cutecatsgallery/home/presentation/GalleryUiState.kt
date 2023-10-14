package com.luishenrique.cutecatsgallery.home.presentation

import com.luishenrique.cutecatsgallery.base.ErrorContent
import com.luishenrique.cutecatsgallery.home.domain.model.Gallery

sealed class GalleryUiState {
    data class Content(val gallery: Gallery) : GalleryUiState()
    data class Error(val errorContent: ErrorContent) : GalleryUiState()
    object Loading : GalleryUiState()
}