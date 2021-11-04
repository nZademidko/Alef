package com.inter.testalef.models.usecases.impl

import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.models.repositories.MainRepository
import com.inter.testalef.models.state.Resource
import com.inter.testalef.models.usecases.GetImagesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImagesUseCaseImpl @Inject constructor(
    private val mainRepository: MainRepository
) : GetImagesUseCase {


    override fun invoke(): Flow<Resource<List<ImageModel>>> = flow {

        emit(Resource.Loading)

        val imageList: List<ImageModel> = mainRepository.fetchImages()

        emit(Resource.Success(imageList))
    }.catch { throwable ->

        emit(Resource.Error(throwable.message.toString()))
    }

}