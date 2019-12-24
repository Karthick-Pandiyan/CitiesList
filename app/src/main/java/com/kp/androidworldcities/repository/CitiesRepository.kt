package com.kp.androidworldcities.repository

import androidx.lifecycle.MutableLiveData
import com.kp.androidworldcities.model.Cities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesRepository {

    val mutableLiveData = MutableLiveData<List<Cities>>()

    fun fetchCitiesList(): MutableLiveData<List<Cities>>  {
        var citiesList = mutableListOf<Cities>()
        var cities = Cities()
        cities.name = "TamilNadu"
        cities.country = "India"
        citiesList.add(cities)
        mutableLiveData.postValue(citiesList)
        return mutableLiveData
    }

}