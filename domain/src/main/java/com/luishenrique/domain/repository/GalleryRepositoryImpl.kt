package com.luishenrique.domain.repository

import com.luishenrique.domain.ApiService
import com.luishenrique.domain.entity.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class GalleryRepositoryImpl(private val apiService: ApiService): GalleryRepository {

    override suspend fun findAllCats(): Gallery? {
        return withContext(Dispatchers.IO) {
            if (apiService.finAllCats().isSuccessful) apiService.finAllCats().body()
            else  throw Exception()
        }
    }
}