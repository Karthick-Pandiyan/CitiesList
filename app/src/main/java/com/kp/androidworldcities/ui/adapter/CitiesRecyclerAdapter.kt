package com.kp.androidworldcities.ui.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kp.androidworldcities.model.Cities
import kotlinx.android.synthetic.main.item_cities.view.*


class CitiesRecyclerAdapter(private val context: Context, private var citiesList: List<Cities>) {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: Cities) {
            itemView.tvCityTitle.text = city.getCityAndCountryTitle()
        }
    }
}