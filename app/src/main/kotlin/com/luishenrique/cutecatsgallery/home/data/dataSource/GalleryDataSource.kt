package com.luishenrique.cutecatsgallery.home.data.dataSource

import com.luishenrique.cutecatsgallery.home.data.network.response.GalleryResponse

interface GalleryDataSource {
    suspend fun finAllCats(page: Int): GalleryResponse

}
