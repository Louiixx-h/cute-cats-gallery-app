package com.luishenrique.cutecatsgallery.home.data.dataSource

import com.luishenrique.cutecatsgallery.home.data.network.HomeApiService
import com.luishenrique.cutecatsgallery.home.data.network.response.GalleryResponse

class GalleryDataSourceImpl(private val service: HomeApiService) : GalleryDataSource {

    override suspend fun finAllCats(page: Int): GalleryResponse {
        return service.finAllCats(page = page)
    }
}