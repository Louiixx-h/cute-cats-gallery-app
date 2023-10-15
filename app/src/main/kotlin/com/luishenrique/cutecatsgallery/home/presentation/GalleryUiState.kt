package com.luishenrique.cutecatsgallery.home.presentation

import com.luishenrique.cutecatsgallery.base.ErrorContent
import com.luishenrique.cutecatsgallery.home.domain.model.Image

sealed class GalleryUiState {
    data class Content(val images: List<Image>) : GalleryUiState()
    data class Error(val errorContent: ErrorContent) : GalleryUiState()
    object Loading : GalleryUiState()
}