package com.memeful.android.model

import com.squareup.moshi.Json

data class GalleryDataResponse(
    @Json(name = "data")
    val data: List<GalleryData>?
)

data class GalleryData(
    @Json(name = "images")
    val images: List<ImageData>?
)
data class ImageData(
    @Json(name = "type")
    val type: String?,
    @Json(name = "link")
    val link: String?
)