package com.example.myrooms.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "tb_favorite")
data class FavoriteEntity(
    @NonNull @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean? = false,
    @ColumnInfo(name = "date") @TypeConverters(Converters::class) var date: Date?,
)
