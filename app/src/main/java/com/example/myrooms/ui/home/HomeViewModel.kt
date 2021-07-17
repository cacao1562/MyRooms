package com.example.myrooms.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.myrooms.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    val roomsProduct = Pager(PagingConfig(20)) {
        HomeDataSource(mainRepository)
    }.flow.cachedIn(viewModelScope)

}