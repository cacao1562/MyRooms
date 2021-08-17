package com.example.myrooms.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRoom(roomEntity: List<RoomEntity>)

    @Query("SELECT * FROM tb_rooms")
    fun getRooms(): PagingSource<Int, RoomEntity>

    @Query("UPDATE tb_rooms SET isFavorite = :favorite, date = :time  WHERE id = :id")
    fun updateFavorite(id: Int, favorite: Boolean, time: Date = Calendar.getInstance(Locale.KOREA).time)

    @Query("SELECT isFavorite FROM tb_rooms WHERE id = :id")
    fun isFavorite(id: Int): Boolean

    @Query("DELETE FROM tb_rooms")
    suspend fun clearRooms()

    @Query("SELECT * FROM tb_rooms WHERE id = :id")
    fun getRoomById(id: Int): LiveData<RoomEntity>

    @Query("SELECT * FROM tb_rooms, tb_favorite WHERE tb_rooms.id = tb_favorite.id ORDER BY date DESC")
    fun getRoombyDateDesc(): Flow<List<RoomEntity>>

    @Query("SELECT * FROM tb_rooms, tb_favorite WHERE tb_rooms.id = tb_favorite.id ORDER BY date ASC")
    fun getRoombyDateAsc(): Flow<List<RoomEntity>>

    @Query("SELECT * FROM tb_rooms, tb_favorite WHERE tb_rooms.id = tb_favorite.id ORDER BY rate DESC")
    fun getRoombyRateDesc(): Flow<List<RoomEntity>>

    @Query("SELECT * FROM tb_rooms, tb_favorite WHERE tb_rooms.id = tb_favorite.id ORDER BY rate ASC")
    fun getRoombyRateAsc(): Flow<List<RoomEntity>>

    /**
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM tb_rooms WHERE id = :id")
    fun deleteRoomById(id: Int)

}