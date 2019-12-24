package com.kp.androidworldcities.viewmodel

import com.kp.androidworldcities.model.Cities
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CitiesViewModelTest {

    var viewModel = CitiesViewModel()

    @Before
    fun setUp() {
    }

    @Test
    fun `Given view model returns Null value from getCitiesList`() {
        viewModel.getCitiesList()
        viewModel.mutableLiveData?.value?.isEmpty()?.let { Assert.assertNull(it) }
    }

    @Test
    fun `Given view model returns values from getCitiesList`(){
        viewModel.getCitiesList()
        viewModel.mutableLiveData?.value?.let { Assert.assertEquals(it.get(0).name, "India") }
    }
}