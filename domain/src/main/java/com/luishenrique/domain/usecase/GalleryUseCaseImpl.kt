package com.luishenrique.domain.usecase

import com.luishenrique.domain.entity.Gallery
import com.luishenrique.domain.handle.Result
import com.luishenrique.domain.repository.GalleryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryUseCaseImpl(private val repository: GalleryRepository): GalleryUseCase {

    override fun findAllCats(page: Int, onSuccess: suspend (Gallery) -> Unit, onError: suspend () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = repository.findAllCats(page)) {
                is Result.Success -> {
                    if (response.data == null) onError.invoke()
                    else onSuccess.invoke(response.data)
                }
                is Result.Error -> {
                    onError.invoke()
                }
            }
        }
    }
}
