package com.example.myrooms.ui.favorites

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myrooms.db.RoomInfoDatabase
import com.example.myrooms.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val database: RoomInfoDatabase
): BaseViewModel(database) {

    val checkNumber: MutableStateFlow<Int> = MutableStateFlow(0)
    private val favoritesFlow = checkNumber.flatMapLatest { index ->
        when(index) {
            0 -> {
                database.roomDao().getRoombyDateDesc()
            }
            1 -> {
                database.roomDao().getRoombyDateAsc()
            }
            2 -> {
                database.roomDao().getRoombyRateDesc()
            }
            3 -> {
                database.roomDao().getRoombyRateAsc()
            }
            else -> {
                database.roomDao().getRoombyDateDesc()
            }
        }
    }

    val favoritesList = favoritesFlow.asLiveData(viewModelScope.coroutineContext)


    fun setCheckNumber(num: Int) {
        checkNumber.value = num
    }

}