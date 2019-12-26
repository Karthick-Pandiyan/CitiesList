package com.kp.androidworldcities.service;


import com.kp.androidworldcities.model.Cities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CitiesAPIService {

    @GET("1bvxgc")
    Call<List<Cities>> getCities();
}

