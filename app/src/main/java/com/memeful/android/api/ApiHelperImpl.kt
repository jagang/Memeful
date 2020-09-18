package com.memeful.android.api

import com.memeful.android.model.GalleryDataResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ImgurService) : ApiHelper {

    override suspend fun getGallery(): Response<GalleryDataResponse> = apiService.getGallery()

}