package com.example.myrooms.ui.home_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.model.Product
import com.example.myrooms.repository.DatabaseRepository
import com.example.myrooms.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): BaseViewModel(databaseRepository) {

    private val _roomEntity = MutableLiveData<RoomEntity>()
    val roomEntity: LiveData<RoomEntity?> = _roomEntity

    fun getRoomById(id: Int) {
        viewModelScope.launch {
            _roomEntity.value = databaseRepository.getRoomById(id)
        }
    }
}