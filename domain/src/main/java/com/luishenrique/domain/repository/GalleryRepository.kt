package com.luishenrique.domain.repository

import androidx.lifecycle.LiveData
import com.luishenrique.domain.entity.Gallery
import com.luishenrique.domain.handle.Result

interface GalleryRepository {
    suspend fun findAllCats(page: Int): Result<Gallery?>
}