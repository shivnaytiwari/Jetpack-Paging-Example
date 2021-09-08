package com.missingsemicollon.pagingexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.missingsemicollon.pagingexample.network.MyApi

@Suppress("UNCHECKED_CAST")
class PassengersViewModelFactory(
    private val api: MyApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PassengersViewModel(api) as T
    }
}