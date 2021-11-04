package com.inter.testalef.models.state

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()

    class Error(val message: String) : Resource<Nothing>()

    class  Success<T>(val data: T): Resource<T>()
}