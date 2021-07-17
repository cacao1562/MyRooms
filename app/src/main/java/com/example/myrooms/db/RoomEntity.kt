package com.example.myrooms.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*


@Entity(tableName = "tb_rooms")
data class RoomEntity @JvmOverloads constructor(
    @NonNull @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "thumbnail") val thumbnail: String? = "",
    @ColumnInfo(name = "imagePath") val imagePath: String? = "",
    @ColumnInfo(name = "subject") val subject: String? = "",
    @ColumnInfo(name = "price") val price: Int? = 0,
    @ColumnInfo(name = "rate") val rate: Float? = 0F,
    @ColumnInfo(name = "date") @TypeConverters(Converters::class) var date: Date?,
) {

}