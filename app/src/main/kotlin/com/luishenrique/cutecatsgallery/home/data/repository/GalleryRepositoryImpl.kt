package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.cutecatsgallery.home.data.dataSource.GalleryDataSource
import com.luishenrique.cutecatsgallery.home.data.mapper.GalleryMapper
import com.luishenrique.cutecatsgallery.home.domain.model.Gallery

class GalleryRepositoryImpl(private val dataSource: GalleryDataSource) : GalleryRepository {

    override suspend fun findAllCats(page: Int): Gallery {
        return GalleryMapper.mapToModel(dataSource.finAllCats(page = page))
    }
}