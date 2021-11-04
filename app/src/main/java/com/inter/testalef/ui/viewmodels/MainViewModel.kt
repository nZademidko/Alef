package com.inter.testalef.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.models.state.Resource
import com.inter.testalef.models.usecases.GetImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {


    private val _listImagesState: MutableStateFlow<Resource<List<ImageModel>>> =
        MutableStateFlow(Resource.Loading)
    val listImagesState get() = _listImagesState.asStateFlow()


    fun refreshImages() {
        _listImagesState.value = Resource.Loading
        loadImages()
    }

    fun loadImages() {
        if (_listImagesState.value !is Resource.Success) {
            getImagesUseCase().onEach { response ->

                when (response) {

                    is Resource.Loading -> {

                        _listImagesState.value = Resource.Loading
                    }

                    is Resource.Error -> {

                        _listImagesState.value = Resource.Error(response.message)
                    }

                    is Resource.Success -> {

                        _listImagesState.value = Resource.Success(response.data)
                    }

                }
            }.launchIn(viewModelScope)
        }
    }

}