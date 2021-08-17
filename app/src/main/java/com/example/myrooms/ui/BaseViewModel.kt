package com.example.myrooms.ui

import androidx.lifecycle.ViewModel
import com.example.myrooms.db.FavoriteEntity
import com.example.myrooms.db.RoomInfoDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val database: RoomInfoDatabase
): ViewModel() {

    fun udpateFavorite(id: Int, selected: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            database.runInTransaction {
                database.roomDao().updateFavorite(id, !selected)
                if (selected) {
                    database.favoriteDao().deleteFavoriteById(id)
                }else {
                    database
                        .favoriteDao()
                        .insertFavorite(FavoriteEntity(
                            id,
                            true,
                            Calendar.getInstance(Locale.KOREA).time))
                }
            }
        }
    }
}