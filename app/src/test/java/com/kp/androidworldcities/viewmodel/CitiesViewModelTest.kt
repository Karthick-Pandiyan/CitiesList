package com.kp.androidworldcities.viewmodel

import com.kp.androidworldcities.model.Cities
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
    fun `Given view model returns values from getCitiesList`() {
        citiesRepository.mutableLiveData.value?.let {
            Assert.assertEquals(
                it.get(0).country,
                "India"
            )
        }
    }

    @Test
    fun `Given view model returns Null value when calling from cities repository`() {
        citiesRepository.mutableLiveData.value.let { Assert.assertNull(it) }
    }

    @Test
    fun `Given view model and repository returns same value when retrieving list of cities`() {
        citiesRepository.fetchCitiesList().value?.let {
            Assert.assertEquals(
                it.get(0).name,
                viewModel.getCitiesList.value?.get(0)?.name
            )
        }
    }

    @Test
    fun `test given view model configuring the data into configureCitiesData function when it returns empty list`() {

        viewModel.configureCitiesData(emptyList())

        Assert.assertEquals(viewModel.existingCitiesList.size, 0)
    }

    @Test
    fun `test given view model configuring the data into configureCitiesData function`() {
        val citiesList = mutableListOf<Cities>()
        val cities = Cities()
        cities.name = "TamilNadu"
        cities.country = "India"
        citiesList.add(cities)

        viewModel.configureCitiesData(citiesList)

        Assert.assertEquals(viewModel.existingCitiesList.get(0).name, "TamilNadu")
    }

    @Test
    fun `test do search function returns not null when original data is empty`() {
        viewModel.configureCitiesData(emptyList())
        Assert.assertNotNull(viewModel.doSearch(""))
    }

    @Test
    fun `Given doSearch function returns empty list when search object not found in the original list`() {
        val citiesList = mutableListOf<Cities>()
        val cities = Cities()
        cities.name = "TamilNadu"
        cities.country = "India"
        citiesList.add(cities)

        viewModel.configureCitiesData(citiesList)
        viewModel.doSearch("test")
            .subscribe {
                viewModel.existingCitiesList.clear()
                viewModel.existingCitiesList.addAll(viewModel.filteredCitiesList)
            }
        Assert.assertEquals(viewModel.existingCitiesList.size, 0)
    }

    @Test
    fun `Given doSearch function returns empty list when search object contains lengthy string and if it doesn't matching list`() {
        val citiesList = mutableListOf<Cities>()
        val cities = Cities()
        cities.name = "TamilNadu"
        cities.country = "India"
        citiesList.add(cities)

        viewModel.configureCitiesData(citiesList)
        viewModel.doSearch("abcdefghijklmnopqrstuvwxyz1234567890sjdhgskjhgbsdkgjbdfhdfh")
            .subscribe {
                viewModel.existingCitiesList.clear()
                viewModel.existingCitiesList.addAll(viewModel.filteredCitiesList)
            }
        Assert.assertEquals(viewModel.existingCitiesList.size, 0)
    }
}