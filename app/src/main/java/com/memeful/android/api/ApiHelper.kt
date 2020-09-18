package com.memeful.android.api
import retrofit2.Response

interface ApiHelper {

    suspend fun getGallery(): Response<Any>
}