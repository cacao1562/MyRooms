package com.example.myrooms.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.db.RoomInfoDatabase
import com.example.myrooms.repository.RoomsRepository
import com.example.myrooms.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeDataViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository,
    private val database: RoomInfoDatabase
): BaseViewModel(database) {

    private var currentRooms: Flow<PagingData<RoomEntity>>? = null

    fun getRooms(): Flow<PagingData<RoomEntity>> {

        val lastResult = currentRooms
        if (lastResult != null) {
            return lastResult
        }
        val rooms = roomsRepository.getRooms().cachedIn(viewModelScope)
        currentRooms = rooms
        return rooms
    }

}