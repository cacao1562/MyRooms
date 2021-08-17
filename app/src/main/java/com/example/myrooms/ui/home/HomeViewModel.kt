package com.example.myrooms.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.db.RoomInfoDatabase
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
    private val database: RoomInfoDatabase
): BaseViewModel(database) {

    val roomsProduct = Pager(PagingConfig(20)) {
        HomeDataSource(mainRepository)
    }.flow.cachedIn(viewModelScope)


//    suspend fun isFavorite(id: Int): Boolean {
//        val result = viewModelScope.async {
//            databaseRepository.getRooms()?.let {
//                return@async it.any { roomEntity ->
//                   roomEntity.id == id
//               }
//            }.run {
//                return@async  false
//            }
//        }
//        return result.await()
//    }

}