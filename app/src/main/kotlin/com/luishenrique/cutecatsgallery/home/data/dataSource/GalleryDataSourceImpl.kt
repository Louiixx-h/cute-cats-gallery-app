package com.luishenrique.cutecatsgallery.home.data.dataSource

import com.luishenrique.cutecatsgallery.home.data.network.HomeApiService
import com.luishenrique.cutecatsgallery.home.data.network.response.ImageResponse

class GalleryDataSourceImpl(private val service: HomeApiService) : GalleryDataSource {

    override suspend fun finAllCats(page: Int): List<ImageResponse> {
        return service.finAllCats(page = page)
    }
}