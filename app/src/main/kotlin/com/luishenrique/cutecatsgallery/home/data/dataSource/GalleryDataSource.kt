package com.luishenrique.cutecatsgallery.home.data.dataSource

import com.luishenrique.cutecatsgallery.home.data.network.response.ImageResponse

interface GalleryDataSource {
    suspend fun finAllCats(page: Int): List<ImageResponse>

}
