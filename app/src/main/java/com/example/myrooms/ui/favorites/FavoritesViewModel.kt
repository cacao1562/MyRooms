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

    val checkNumber = MutableLiveData<Int>(0)

    fun setCheckNumber(num: Int) {
        checkNumber.value = num
    }

    fun getRoomsSorted(num: Int) {
        viewModelScope.launch {
            when(num) {
                0 -> {
                    databaseRepository.getRoombyDateDesc()?.let { _favoritesList.value = it }
                }
                1 -> {
                    databaseRepository.getRoombyDateAsc()?.let { _favoritesList.value = it }
                }
                2 -> {
                    databaseRepository.getRoombyRateDesc()?.let { _favoritesList.value = it }
                }
                3 -> {
                    databaseRepository.getRoombyRateAsc()?.let { _favoritesList.value = it }
                }
            }
        }

    }

}