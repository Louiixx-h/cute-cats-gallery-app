package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.domain.ApiService
import com.luishenrique.domain.entity.Gallery
import com.luishenrique.domain.handle.Result
import retrofit2.Response

class GalleryRepositoryImpl(private val apiService: ApiService): GalleryRepository {

    override suspend fun findAllCats(page: Int): Result<Gallery?> {
        return try {
            val response = apiService.finAllCats(page = page)
            if (response.isSuccessful) Result.Success(data = response.body())
            else Result.Error(exception = Exception("Falha ao buscar imagens."))
        } catch (e: Exception) {
            Result.Error(exception = Exception("Falha ao buscar imagens."))
        }
    }
}