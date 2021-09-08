package com.missingsemicollon.pagingexample.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.missingsemicollon.pagingexample.network.MyApi
import com.missingsemicollon.pagingexample.R
import com.missingsemicollon.pagingexample.viewmodel.PassengersViewModel
import com.missingsemicollon.pagingexample.viewmodel.PassengersViewModelFactory
import kotlinx.android.synthetic.main.fragment_passangers.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class PassengersFragment : Fragment(R.layout.fragment_passangers) {

    private lateinit var viewModel: PassengersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = PassengersViewModelFactory(MyApi())
        viewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)

        val passengersAdapter = PassengersAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = passengersAdapter

        lifecycleScope.launch {
            viewModel.passengers.collectLatest { pagedData ->
                Timber.e("data fetched")
                passengersAdapter.submitData(pagedData)
            }
        }
    }

}