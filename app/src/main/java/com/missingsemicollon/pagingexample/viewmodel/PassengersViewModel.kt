package com.missingsemicollon.pagingexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.missingsemicollon.pagingexample.network.MyApi
import com.missingsemicollon.pagingexample.utils.PassengersDataSource

class PassengersViewModel(
    private val api: MyApi
) : ViewModel() {
    val passengers = Pager(PagingConfig(pageSize = 10)) {
        PassengersDataSource(api)
    }.flow.cachedIn(viewModelScope)
}