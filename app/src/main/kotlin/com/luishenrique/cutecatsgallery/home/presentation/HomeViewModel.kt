package com.luishenrique.cutecatsgallery.home.presentation

import com.luishenrique.cutecatsgallery.base.BaseViewModel
import com.luishenrique.cutecatsgallery.home.domain.GalleryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val useCase: GalleryUseCase,
    private var catsJob: Job
): BaseViewModel() {

    private val _uiState = MutableStateFlow<GalleryUiState>(GalleryUiState.Loading)
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        getCats()
    }

    fun getCats() {
        catsJob.cancel()
        catsJob = request(
            block = { useCase.findAllCats(1) },
            onStartRequest = { _uiState.value = GalleryUiState.Loading },
            onSuccess = { _uiState.value = GalleryUiState.Content(it) },
            onError = { _uiState.value = GalleryUiState.Error(it) },
        )
    }
}