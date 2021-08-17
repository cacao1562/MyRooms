package com.example.myrooms.ui.home_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myrooms.db.RoomInfoDatabase
import com.example.myrooms.ui.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeDetailViewModel @AssistedInject constructor(
    private val database: RoomInfoDatabase,
    @Assisted private val id: Int
): BaseViewModel(database) {

    val roomEntity = database.roomDao().getRoomById(id)

    @AssistedFactory
    interface HomeDetailViewModelFactory {
        fun create(id: Int): HomeDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: HomeDetailViewModelFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }

}