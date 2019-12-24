package com.kp.androidworldcities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kp.androidworldcities.model.Cities

class CitiesViewModel {

    fun getCitiesList(): LiveData<List<Cities>> {
        return MutableLiveData<List<Cities>>()
    }
}