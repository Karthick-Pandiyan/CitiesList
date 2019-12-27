package com.kp.androidworldcities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.repository.CitiesRepository
import io.reactivex.Completable
import androidx.lifecycle.MutableLiveData


class CitiesViewModel :  ViewModel() {

    val errorMessage = MutableLiveData<String>()
    private val citiesRepository = CitiesRepository()
    private var originalCitiesList = listOf<Cities>()
    val existingCitiesList : MutableList<Cities> = mutableListOf()
    val filteredCitiesList : MutableList<Cities> = mutableListOf()

    val getCitiesList : LiveData<List<Cities>>
        get() = this.citiesRepository.fetchCitiesList()

    fun configureCitiesData(citiesList : List<Cities>){
        originalCitiesList = citiesList
        existingCitiesList.addAll(citiesList)
    }

    fun doSearch(query : String): Completable = Completable.create {
        val extractedList = originalCitiesList.filter {
            it.name?.contains(query)==true || it.country?.contains(query)==true
        }.toList()

        filteredCitiesList.clear()
        filteredCitiesList.addAll(extractedList)
        it.onComplete()
    }

    fun isErrorFromResponse(size : Int?, errorDescription: String){
        if(size == null || size == 0){
            errorMessage.value = errorDescription

        }
    }

}