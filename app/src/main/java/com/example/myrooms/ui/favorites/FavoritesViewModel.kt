package com.example.myrooms.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {

    val favoritesData: LiveData<List<RoomEntity>> =
        databaseRepository.getFavorites().asLiveData()
}