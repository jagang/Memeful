package com.memeful.android.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.memeful.android.model.GalleryDataResponse
import com.memeful.android.repo.ImgurRepository
import com.memeful.android.utils.NetworkHelper
import com.memeful.android.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val imgurRepository: ImgurRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val getGalleryMutableLiveData = MutableLiveData<Resource<GalleryDataResponse>>()
    val getGalleryLiveData: LiveData<Resource<GalleryDataResponse>> get() = getGalleryMutableLiveData

    var pageNo = 0
    var isLastPage = false

    init {
        fetchGallery()
    }

    private fun fetchGallery() {
        viewModelScope.launch {
            getGalleryMutableLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                imgurRepository.getGallery(pageNo++).let {
                    if (it.isSuccessful) getGalleryMutableLiveData.postValue(Resource.success(it.body()))
                    else getGalleryMutableLiveData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else getGalleryMutableLiveData.postValue(Resource.error("No internet connection", null))
        }
    }

    fun loadNextPage() {
        if (isLastPage.not()) {
            fetchGallery()
        }
    }

}