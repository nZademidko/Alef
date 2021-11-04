package com.inter.testalef.di.modules


import com.inter.testalef.data.AppService
import com.inter.testalef.data.AppServiceBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAnimeService(): AppService = AppServiceBuilder().build()
}