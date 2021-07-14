package com.example.myrooms

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrooms.model.RoomInfo
import com.example.myrooms.repository.MainRepository
import com.skydoves.whatif.whatIf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _toastMessage: MutableLiveData<String> = MutableLiveData()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _roomInfo = MutableLiveData<RoomInfo>()
    val roomInfo : LiveData<RoomInfo> = _roomInfo

    private val roomsFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val roomInfoFlow = roomsFetchingIndex.flatMapLatest { page ->
        mainRepository.fetchRooms(
            page = page,
            onStart = { _isLoading.postValue(true) },
            onComplete = { _isLoading.postValue(false) },
            onError = { _toastMessage.postValue(it) }
        )
    }


    init {
        viewModelScope.launch {
            roomInfoFlow.collect {
                Log.d("MainViewModel", "usersListFlow = $it")
                _roomInfo.value = it
            }
        }
    }

    @MainThread
    fun fetchRooms() {
        whatIf(!_isLoading.value!!) {
            roomsFetchingIndex.value++
        }
    }
}