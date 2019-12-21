package com.kp.worldcities.service;


import com.kp.worldcities.model.Cities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CitiesAPIService {

    @GET("ztwdk")
    Call<List<Cities>> getCities();
}

