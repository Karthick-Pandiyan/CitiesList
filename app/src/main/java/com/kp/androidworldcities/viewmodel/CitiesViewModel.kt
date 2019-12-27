package com.kp.androidworldcities.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.repository.CitiesRepository
import io.reactivex.Completable
import androidx.lifecycle.MutableLiveData


class CitiesViewModel :  ViewModel() {

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

    @SuppressLint("DefaultLocale")
    fun doSearch(query : String): Completable = Completable.create {
        val extractedList = originalCitiesList.filter {
            it.name?.toLowerCase()?.contains(query.toLowerCase())==true ||
                    it.country?.toLowerCase()?.contains(query.toLowerCase())==true
        }.toList()

        filteredCitiesList.clear()
        filteredCitiesList.addAll(extractedList)
        it.onComplete()
    }

    val isErrorFromResponse : MutableLiveData<String>
        get() = citiesRepository.errorMessage

}