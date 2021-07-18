package com.example.myrooms.repository

import com.example.myrooms.db.RoomDao
import com.example.myrooms.db.RoomEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class DatabaseRepository  @Inject constructor(
   private val dao: RoomDao
) {

   private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

   suspend fun insertRoom(roomEntity: RoomEntity) =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.insertRoom(roomEntity)
         }catch (e: Exception) {
            null
         }
      }

   suspend fun deleteRoomById(id: Int) =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.deleteRoomById(id)
         }catch (e: Exception){
            null
         }
      }

   suspend fun getRooms() =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.getRooms()
         }catch (e: Exception){
            null
         }
      }

   suspend fun getRoomById(id: Int) =
      withContext(ioDispatcher) {
         return@withContext try {
             dao.getRoomById(id)
         }catch (e: Exception) {
            null
         }
      }

}