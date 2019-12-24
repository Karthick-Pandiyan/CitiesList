package com.kp.androidworldcities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kp.androidworldcities.model.Cities

class CitiesViewModel {

    var mutableLiveData: MutableLiveData<List<Cities>>? = null

    fun getCitiesList(): Unit? {
        return mutableLiveData?.setValue(null)
    }
}