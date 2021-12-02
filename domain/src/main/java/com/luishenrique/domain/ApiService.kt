package com.luishenrique.domain

import com.luishenrique.domain.entity.Gallery
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("gallery/search/?q=cats")
    suspend fun finAllCats(): Response<Gallery>
}