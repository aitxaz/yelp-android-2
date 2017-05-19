package com.example.androidtest.items;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RCAPPSMac3 on 19/05/2017.
 */

public class Globals {


    public static final String RESTURANT ="resturant" ;
    public static final String RATING = "rating";
    public static final String CATEGORIES = "categories";
    public static final String INDEXT = "index";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
