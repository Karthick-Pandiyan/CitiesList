package com.kp.worldcities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kp.worldcities.model.Cities
import com.kp.worldcities.repository.CitiesRepository
import io.reactivex.Completable

class CitiesViewModel : ViewModel() {

    private val citiesRepository : CitiesRepository
    private var originalCitiesList = listOf<Cities>()
    val filteredCitiesList : MutableList<Cities> = mutableListOf()
    val existingCitiesList : MutableList<Cities> = mutableListOf()

    init {
        citiesRepository = CitiesRepository()
    }

    val getAllCities : LiveData<List<Cities>>
        get() = this.citiesRepository.getMutableLiveData()

    fun configureCitiesData(citiesList : List<Cities>){
        originalCitiesList = citiesList;
        existingCitiesList.addAll(citiesList)

    }

    fun search(query : String): Completable = Completable.create {
        val extractedList = originalCitiesList.filter {
            it.name?.contains(query)==true || it.country?.contains(query)==true
        }.toList()

        filteredCitiesList.clear()
        filteredCitiesList.addAll(extractedList)
        it.onComplete()
    }
}