package com.example.myrooms.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM tb_favorite")
    fun getFavorites(): LiveData<List<FavoriteEntity>>

    @Query("SELECT isFavorite FROM tb_favorite WHERE id = :id")
    fun isFavorite(id: Int): Boolean

    @Query("SELECT date FROM tb_favorite WHERE id = :id")
    fun getInsertedDate(id: Int): Date

    @Query("DELETE FROM tb_favorite WHERE id = :id")
    fun deleteFavoriteById(id: Int): Int

}