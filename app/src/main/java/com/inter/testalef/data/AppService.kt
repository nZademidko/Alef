package com.inter.testalef.data

import com.inter.testalef.models.entities.remote.ImageModelRemote
import retrofit2.http.GET

interface AppService {

    @GET("task-m-001/list.php")
    suspend fun getImages(): ImageModelRemote
}