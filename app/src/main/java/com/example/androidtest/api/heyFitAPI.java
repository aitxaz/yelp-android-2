package com.example.androidtest.api;


import com.example.androidtest.web_responses.search_resturant.SearchResturantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Muhammad Abid on 11/28/2016.
 */

public interface heyFitAPI {

    @GET("businesses/search?term=delis")
    Call<SearchResturantResponse> getResturantsList(@Header("Authorization") String authorizationKey, @Query("latitude") double latitude, @Query("longitude") double longitude);
}
