package com.memeful.android.api
import com.memeful.android.model.GalleryDataResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getGallery(): Response<GalleryDataResponse>
}