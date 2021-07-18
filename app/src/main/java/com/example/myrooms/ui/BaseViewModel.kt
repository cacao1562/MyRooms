package com.example.myrooms.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {

    fun insertRoom(roomEntity: RoomEntity) {
        viewModelScope.launch {
            databaseRepository.insertRoom(roomEntity)
        }
    }

    fun deleteRoomById(id: Int) {
        viewModelScope.launch {
            databaseRepository.deleteRoomById(id)
        }
    }
}