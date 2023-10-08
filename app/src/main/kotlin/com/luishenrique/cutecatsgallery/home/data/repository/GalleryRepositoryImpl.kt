package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.cutecatsgallery.home.data.mapper.GalleryMapper
import com.luishenrique.cutecatsgallery.home.data.network.ApiService
import com.luishenrique.cutecatsgallery.home.domain.model.Gallery

class GalleryRepositoryImpl(private val service: ApiService) : GalleryRepository {

    override suspend fun findAllCats(page: Int): Gallery {
        return GalleryMapper.mapToModel(service.finAllCats(page = page))
    }
}