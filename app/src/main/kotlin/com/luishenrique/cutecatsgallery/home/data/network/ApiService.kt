package com.luishenrique.cutecatsgallery.home.data.network

import com.luishenrique.domain.entity.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("gallery/search/?q=cats")
    suspend fun finAllCats(
        @Header("Authorization") authHeader: String = "Client-ID 1ceddedc03a5d71",
        @Query("page") page: Int = 1
    ): Response<Gallery?>
}