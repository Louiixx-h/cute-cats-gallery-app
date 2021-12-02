package com.luishenrique.cutecatsgallery.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luishenrique.domain.entity.Gallery
import com.luishenrique.domain.usecase.GalleryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GalleryUseCase): ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _gallery: MutableLiveData<Gallery> = MutableLiveData()
    val gallery: LiveData<Gallery> = _gallery

    fun findAllCats() {
       fetchData{
           _gallery.postValue(useCase.findAllCats())
       }
    }

    private fun fetchData(call: suspend () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            _loading.postValue(true)
            call.invoke()
            _loading.postValue(false)
        }
    }
}