package com.kp.worldcities.repository

import androidx.lifecycle.MutableLiveData
import com.kp.worldcities.model.Cities
import com.kp.worldcities.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesRepository {

    internal val mutableLiveData = MutableLiveData<List<Cities>>()

    fun getMutableLiveData(): MutableLiveData<List<Cities>>  {
            val apiService = RetrofitInstance.apiService
            val fetchCities = apiService.cities

            fetchCities.enqueue(object : Callback<List<Cities>> {
                override fun onResponse(call: Call<List<Cities>>, response: Response<List<Cities>>) {
                    val cities = response.body()
                    mutableLiveData.value = cities
                }

                override fun onFailure(call: Call<List<Cities>>, t: Throwable) {
                   mutableLiveData.value = null
                }
            })
            return mutableLiveData;
        }

}