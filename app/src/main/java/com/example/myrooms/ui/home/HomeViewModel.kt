package com.example.myrooms.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.repository.DatabaseRepository
import com.example.myrooms.repository.MainRepository
import com.example.myrooms.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val databaseRepository: DatabaseRepository
): BaseViewModel(databaseRepository) {

    val roomsProduct = Pager(PagingConfig(20)) {
        HomeDataSource(mainRepository)
    }.flow.cachedIn(viewModelScope)


//    fun insertRoom(roomEntity: RoomEntity) {
//        viewModelScope.launch {
//            databaseRepository.insertRoom(roomEntity)
//        }
//    }
//
//    fun deleteRoomById(id: Int) {
//        viewModelScope.launch {
//            databaseRepository.deleteRoomById(id)
//        }
//    }

    suspend fun isFavorite(id: Int): Boolean {
        val result = viewModelScope.async {
            databaseRepository.getRooms()?.let {
                return@async it.any { roomEntity ->
                   roomEntity.id == id
               }
            }.run {
                return@async  false
            }
        }
        return result.await()
    }

}