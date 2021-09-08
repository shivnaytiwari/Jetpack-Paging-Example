package com.missingsemicollon.pagingexample.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.missingsemicollon.pagingexample.Passenger
import com.missingsemicollon.pagingexample.R
import com.missingsemicollon.pagingexample.utils.loadImage
import kotlinx.android.synthetic.main.item_passenger.view.*

class PassengersAdapter :
    PagingDataAdapter<Passenger, PassengersAdapter.PassengersViewHolder>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PassengersViewHolder {
        return PassengersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_passenger, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class PassengersViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: Passenger) {
            view.image_view_airlines_logo.loadImage(item.airline[0].logo)
            view.text_view_headquarters.text = item.airline[0].head_quaters
            view.text_view_name_with_trips.text = "${item.name}, ${item.trips} Trips"
        }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Passenger>() {
        override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
            return oldItem == newItem
        }
    }
}