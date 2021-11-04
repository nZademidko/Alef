package com.inter.testalef.di.modules

import com.inter.testalef.models.usecases.GetImagesUseCase
import com.inter.testalef.models.usecases.impl.GetImagesUseCaseImpl
import dagger.Binds
import dagger.Module


@Module
interface UseCaseModule {


    @Binds
    fun bindGetImagesUseCase(useCase: GetImagesUseCaseImpl): GetImagesUseCase

}