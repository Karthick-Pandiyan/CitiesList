package com.kp.androidworldcities.viewmodel

import androidx.lifecycle.LiveData
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.repository.CitiesRepository

class CitiesViewModel {

    private val citiesRepository = CitiesRepository()

    val existingCitiesList : MutableList<Cities> = mutableListOf()

    val getCitiesList : LiveData<List<Cities>>
        get() = this.citiesRepository.fetchCitiesList()

    fun configureCitiesData(citiesList : List<Cities>){
        existingCitiesList.addAll(citiesList)
    }


}