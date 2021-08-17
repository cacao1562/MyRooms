package com.example.myrooms.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.myrooms.api.ApiService
import com.example.myrooms.db.RemoteKeys
import com.example.myrooms.db.RoomEntity
import com.example.myrooms.db.RoomInfoDatabase
import com.example.myrooms.model.RoomsResponse
import com.example.myrooms.model.asEntity
import com.skydoves.sandwich.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RoomsMediator @Inject constructor(
    private val service: ApiService,
    private val database: RoomInfoDatabase
): RemoteMediator<Int, RoomEntity>() {

    private val remoteKeyDao = database.remoteKeyDao()
    private val roomDao = database.roomDao()
    private val favoriteDao = database.favoriteDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RoomEntity>
    ): MediatorResult {
        try {
            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> {
                    Log.i("//RoomsMediator load//","REFRESH")
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE
                }

                LoadType.PREPEND -> {
                    Log.i("//RoomsMediator load//","PREPEND")
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKey?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    prevKey
                }

                LoadType.APPEND -> {
                    Log.i("//RoomsMediator load//","APPEND")
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    nextKey
                }
            }


            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val apiResponse = service.fetchRoomsNew(loadKey)
            val rooms = apiResponse.asEntity()
            val endOfPaginationReached = rooms.isEmpty()

            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearRemoteKeys()
                    roomDao.clearRooms()
                }

                val prevKey = if (loadKey == STARTING_PAGE) null else loadKey - 1
                val nextKey = if (endOfPaginationReached) null else loadKey + 1
                val keys = rooms.map { roomInfo ->
                    roomInfo.isFavorite = favoriteDao.isFavorite(roomInfo.id)
                    roomInfo.date = favoriteDao.getInsertedDate(roomInfo.id)
                    RemoteKeys(roomId = roomInfo.id.toLong(), nextKey = nextKey, prevKey = prevKey)
                }

                roomDao.insertAllRoom(rooms)
                remoteKeyDao.insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun fetchRoomsResponse(
        page: Int,
        state: PagingState<Int, RoomEntity>
    ): ApiResponse<RoomsResponse> {
        return service.fetchRooms(page)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RoomEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { rooId ->
                remoteKeyDao.remoteKeysRoomId(rooId)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RoomEntity>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { lastArticle ->
            remoteKeyDao.remoteKeysRoomId(lastArticle.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RoomEntity>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { firstArticle ->
            remoteKeyDao.remoteKeysRoomId(firstArticle.id)
        }
    }

    companion object {
        private const val STARTING_PAGE = 1
    }

}