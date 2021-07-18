package com.example.myrooms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {

    @Query("SELECT * FROM tb_rooms")
    suspend fun getRooms(): List<RoomEntity>

    @Query("SELECT * FROM tb_rooms WHERE id = :id")
    suspend fun getRoomById(id: Int): RoomEntity?

    @Query("SELECT * FROM tb_rooms WHERE date ORDER BY date DESC")
    suspend fun getRoombyDateDesc(): List<RoomEntity>?

    @Query("SELECT * FROM tb_rooms WHERE date ORDER BY date ASC")
    suspend fun getRoombyDateAsc(): List<RoomEntity>?

    @Query("SELECT * FROM tb_rooms WHERE rate ORDER BY rate DESC")
    suspend fun getRoombyRateDesc(): List<RoomEntity>?

    @Query("SELECT * FROM tb_rooms WHERE rate ORDER BY rate ASC")
    suspend fun getRoombyRateAsc(): List<RoomEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(roomEntity: RoomEntity): Long

    /**
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM tb_rooms WHERE id = :id")
    suspend fun deleteRoomById(id: Int): Int

}