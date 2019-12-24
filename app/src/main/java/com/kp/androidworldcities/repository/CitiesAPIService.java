package com.kp.androidworldcities.repository;


import com.kp.androidworldcities.model.Cities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CitiesAPIService {

    @GET("ztwdk")
    Call<List<Cities>> getCities();
}

