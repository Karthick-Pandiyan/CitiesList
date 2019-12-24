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
    fun `test given view model returns Null value from getCitiesList`() {
        viewModel.getCitiesList()
        viewModel.mutableLiveData?.value?.isEmpty()?.let { Assert.assertNull(it) }
    }
}