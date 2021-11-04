package com.inter.testalef.models.usecases

import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.models.state.Resource
import kotlinx.coroutines.flow.Flow

interface GetImagesUseCase {


    operator fun invoke(): Flow<Resource<List<ImageModel>>>
}