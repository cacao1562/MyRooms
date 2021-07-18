package com.example.myrooms.ui.favorites

import androidx.lifecycle.*
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.repository.DatabaseRepository
import com.example.myrooms.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): BaseViewModel(databaseRepository) {

    private val _favoritesList = MutableLiveData<List<RoomEntity>>().apply { value = emptyList() }
    val favoritesList: LiveData<List<RoomEntity>?> = _favoritesList

    fun getRooms() {
        viewModelScope.launch {
            databaseRepository.getRooms()?.let {
                _favoritesList.value = it
            }
        }
    }

}