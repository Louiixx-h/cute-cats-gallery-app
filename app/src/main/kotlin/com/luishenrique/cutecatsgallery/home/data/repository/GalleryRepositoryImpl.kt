package com.luishenrique.cutecatsgallery.home.data.repository

import com.luishenrique.cutecatsgallery.home.data.dataSource.GalleryDataSource
import com.luishenrique.cutecatsgallery.home.data.mapper.GalleryMapper
import com.luishenrique.cutecatsgallery.home.domain.model.Image

class GalleryRepositoryImpl(
    private val dataSource: GalleryDataSource,
    private val mapper: GalleryMapper
) : GalleryRepository {

    override suspend fun findAllCats(page: Int): List<Image> {
        return mapper.toUiModel(dataSource.finAllCats(page = page))
    }
}