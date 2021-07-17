package com.example.myrooms.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class RoomsResponse(
    @Json(name = "msg") val msg: String,
    @Json(name = "code") val code: Int,
    @Json(name = "data") val data: RoomInfo,
)

@JsonClass(generateAdapter = true)
data class RoomInfo(
    @Json(name = "totalCount") val totalCount: Int,
    @Json(name = "product") val product: List<Product>,
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "description") val description: Description,
    @Json(name = "rate") val rate: Float
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "imagePath") val imagePath: String,
    @Json(name = "subject") val subject: String,
    @Json(name = "price") val price: Int
): Parcelable