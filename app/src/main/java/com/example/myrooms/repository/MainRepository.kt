package com.example.myrooms.repository

import androidx.annotation.WorkerThread
import com.example.myrooms.api.ApiService
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: ApiService
) {

    @WorkerThread
    fun fetchRooms(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = service.fetchRooms(page)
        response.suspendOnSuccess {
            val users = data.data
            emit(users)
        }.onError {
            onError("[Code: ${statusCode.code}]: ${message()}")
        }.onException {
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}