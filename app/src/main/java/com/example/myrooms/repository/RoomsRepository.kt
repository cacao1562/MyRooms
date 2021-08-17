package com.example.myrooms.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myrooms.api.ApiService
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.db.RoomInfoDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomsRepository @Inject constructor(
    private val service: ApiService,
    private val database: RoomInfoDatabase
) {

    fun getRooms(): Flow<PagingData<RoomEntity>> {

        val pagingSourceFactory = { database.roomDao().getRooms() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RoomsMediator(service, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}