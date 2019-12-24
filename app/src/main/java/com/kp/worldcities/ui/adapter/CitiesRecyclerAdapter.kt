package com.kp.worldcities.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kp.worldcities.R
import com.kp.worldcities.model.Cities
import kotlinx.android.synthetic.main.item_cities.view.*

class CitiesRecyclerAdapter(private val context: Context, private var citiesList: List<Cities>):
        RecyclerView.Adapter<CitiesRecyclerAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_cities, parent, false)
        )
    }

    override fun getItemCount() = citiesList.size

    override fun onBindViewHolder(holer: CityViewHolder, position: Int){
        holer.bind(citiesList[position])
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: Cities) {
            itemView.tvCityTitle.text = city.getCityAndCountryTitle()
        }
    }

    fun loadCities(citiesList: List<Cities>){
        this.citiesList = citiesList
        notifyDataSetChanged()
    }
}