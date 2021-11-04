package com.inter.testalef.models.repositories

import com.inter.testalef.data.AppService
import com.inter.testalef.extensions.toImageModel
import com.inter.testalef.models.entities.model.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val appService: AppService
) {

    suspend fun fetchImages() = withContext(Dispatchers.IO) {
        appService.getImages().run {
            this.toImageModel()
        }
    }
}