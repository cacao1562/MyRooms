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

   suspend fun getRoombyDateDesc() =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.getRoombyDateDesc()
         }catch (e: Exception) {
            null
         }
      }

   suspend fun getRoombyDateAsc() =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.getRoombyDateAsc()
         }catch (e: Exception) {
            null
         }
      }

   suspend fun getRoombyRateDesc() =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.getRoombyRateDesc()
         }catch (e: Exception) {
            null
         }
      }

   suspend fun getRoombyRateAsc() =
      withContext(ioDispatcher) {
         return@withContext try {
            dao.getRoombyRateAsc()
         }catch (e: Exception) {
            null
         }
      }
}