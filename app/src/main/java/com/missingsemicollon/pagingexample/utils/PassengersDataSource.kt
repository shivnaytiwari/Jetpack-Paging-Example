package com.missingsemicollon.pagingexample.utils

import androidx.paging.PagingSource
import com.missingsemicollon.pagingexample.Passenger
import com.missingsemicollon.pagingexample.network.MyApi
import timber.log.Timber

class PassengersDataSource(
    private val api: MyApi
) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = api.getPassengersData(nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            Timber.e("Error : " + e.message)
            LoadResult.Error(e)
        }
    }
}