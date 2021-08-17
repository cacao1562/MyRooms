package com.example.myrooms.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myrooms.model.Product
import com.example.myrooms.repository.MainRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val repository: MainRepository
): PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {

            val currentLoadingPageKey = params.key ?: 1

            val response = repository.fetchRooms(
                page = currentLoadingPageKey,
                onStart = {},
                onComplete = {},
                onError = { throw Exception(it) }
            )
            val responseData = response.first()?.product

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData?: emptyList(),
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}