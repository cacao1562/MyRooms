package com.example.myrooms.model

import android.os.Parcelable
import com.example.myrooms.db.RoomEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class RoomsResponse(
    @Json(name = "msg") val msg: String,
    @Json(name = "code") val code: Int,
    @Json(name = "data") val data: RoomInfo?,
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
    @Json(name = "rate") val rate: Double
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "imagePath") val imagePath: String,
    @Json(name = "subject") val subject: String,
    @Json(name = "price") val price: Int
): Parcelable

fun RoomsResponse.asEntity(): List<RoomEntity> {
    return data?.let {
        it.product.map {
            RoomEntity(
                id = it.id,
                title = it.name,
                thumbnail = it.thumbnail,
                imagePath = it.description.imagePath,
                subject = it.description.subject,
                price = it.description.price,
                rate = it.rate,
                date = null,
                isFavorite = false
            )
        }
    }?: emptyList()
}