package com.kp.androidworldcities.repository

import androidx.lifecycle.MutableLiveData
import com.kp.androidworldcities.model.Cities
import com.kp.androidworldcities.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesRepository {

    internal val mutableLiveData = MutableLiveData<List<Cities>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchCitiesList(): MutableLiveData<List<Cities>>  {
        val apiService = RetrofitInstance.apiService
        val fetchCities = apiService.cities

        fetchCities.enqueue(object : Callback<List<Cities>> {
            override fun onResponse(call: Call<List<Cities>>, response: Response<List<Cities>>) {
                val cities = response.body()
                if(null != cities) {
                    mutableLiveData.value = cities
                }else{
                    errorMessage.value = "Error!"
                }
            }

            override fun onFailure(call: Call<List<Cities>>, t: Throwable) {
                errorMessage.value = t.toString()
            }
        })
        return mutableLiveData;
    }


}