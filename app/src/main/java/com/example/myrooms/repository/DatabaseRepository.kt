package com.example.myrooms.repository

import com.example.myrooms.db.RoomDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepository  @Inject constructor(
   private val dao: RoomDao
) {

   fun getFavorites() = flow {
      val rooms = dao.getRooms()
      emit(rooms)
   }.flowOn(Dispatchers.Default)

}