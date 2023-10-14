package com.luishenrique.cutecatsgallery.home.data.network

import com.luishenrique.cutecatsgallery.home.data.network.response.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApiService {

    @GET("gallery/search/?q=cats")
    suspend fun finAllCats(
        @Header("Authorization") authHeader: String = "Client-ID 1ceddedc03a5d71",
        @Query("sort") sort: String = "viral",
        @Query("q_type") type: String = "jpg",
        @Query("q_size_px") size: String = "big",
        @Query("page") page: Int = 1
    ): GalleryResponse
}