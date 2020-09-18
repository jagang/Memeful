package com.memeful.android.repo

import com.memeful.android.api.ApiHelper
import javax.inject.Inject

class ImgurRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getGallery() =  apiHelper.getGallery()

}