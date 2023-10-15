package com.luishenrique.cutecatsgallery.home.data.network

import com.luishenrique.cutecatsgallery.BuildConfig
import com.luishenrique.cutecatsgallery.home.data.network.response.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApiService {

    @GET(SEARCH)
    suspend fun finAllCats(
        @Header("x-api-key") apiKey: String = BuildConfig.API_KEY,
        @Query("mime_types") type: String = "jpg,gif,png",
        @Query("limit") limit: Int = 20,
        @Query("size") size: String = "full",
        @Query("page") page: Int = 1
    ): List<ImageResponse>

    companion object {
        private const val SEARCH = "images/search"
    }
}