package com.example.androidtest.interfaces;

import com.example.androidtest.web_responses.search_resturant.SearchResturantResponse;

/**
 * Created by RCAPPSMac3 on 19/05/2017.
 */

public interface SearchCallback {
    void getResturantsList(SearchResturantResponse resturantResponse);
}
