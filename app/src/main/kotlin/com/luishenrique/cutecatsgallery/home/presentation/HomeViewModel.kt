package com.luishenrique.cutecatsgallery.home.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luishenrique.domain.entity.Gallery
import androidx.lifecycle.viewModelScope
import com.luishenrique.domain.usecase.GalleryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GalleryUseCase): ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _gallery: MutableLiveData<Gallery> = MutableLiveData()
    val gallery: LiveData<Gallery> = _gallery

    private val _stateHome: MutableLiveData<Int> = MutableLiveData()
    val stateHome: LiveData<Int> = _stateHome

    private val _page: MutableLiveData<Int> = MutableLiveData(1)

    fun findAllCats() {
        _loading.postValue(true)
        useCase.findAllCats(page = _page.value!!, onSuccess = {
            _page.postValue(_page.value?.plus(1))
            _gallery.postValue(it)
            _loading.postValue(false)
            _stateHome.postValue(View.VISIBLE)
        }, {
            _stateHome.postValue(View.INVISIBLE)
            delay(500)
            _loading.postValue(false)
        })
    }
}