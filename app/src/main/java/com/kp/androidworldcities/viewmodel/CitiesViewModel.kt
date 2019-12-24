package com.kp.androidworldcities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.repository.CitiesRepository

class CitiesViewModel {

    private val citiesRepository : CitiesRepository
    var mutableLiveData: MutableLiveData<List<Cities>>? = null

    init {
        citiesRepository = CitiesRepository()
    }

    val getCitiesList : MutableLiveData<List<Cities>>?
        get() = this.citiesRepository.fetchCitiesList()

}