package com.kp.androidworldcities.viewmodel

import com.kp.androidworldcities.repository.CitiesRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CitiesViewModelTest {

    var viewModel = CitiesViewModel()
    var citiesRepository = CitiesRepository()


    @Test
    fun `Given view model returns Null value from getCitiesList`() {
        citiesRepository.mutableLiveData.value?.isEmpty()?.let { Assert.assertNull(it) }
    }

    @Test
    fun `Given view model returns values from getCitiesList`(){
        citiesRepository.mutableLiveData.value?.let { Assert.assertEquals(it.get(0).name, "India") }
    }

    @Test
    fun `Given view model returns Null value when calling from cities repository`(){
        citiesRepository.mutableLiveData.value.let { Assert.assertNull(it) }
    }

    @Test
    fun `Given view model and repository returns same value when retrieving list of cities`(){
        citiesRepository.mutableLiveData.value?.let { Assert.assertEquals(it.get(0).name,
            viewModel.getCitiesList.value?.get(0)?.name) }
    }
}