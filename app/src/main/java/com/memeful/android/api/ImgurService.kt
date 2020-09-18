package com.memeful.android.api

import com.memeful.android.model.GalleryDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ImgurService {

    @Headers("Authorization: Client-ID 43cab01cae3ec1a")
    @GET("gallery/hot/viral/all/{page}}?showViral=true&mature=false&album_previews=true")
    suspend fun getGallery(@Path("page") pageNo: Int): Response<GalleryDataResponse>
}