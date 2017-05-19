package com.example.androidtest.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muhammad Abid on 12/7/2016.
 */

public class MySharedPreference {
    private static final String LOGIN_PARAMETERS = "login_parameters";
    private static final String ISSERVICESSTARTED = "isServiceStarted";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "pref";


    public MySharedPreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isServiceStarted() {
        return pref.getBoolean(ISSERVICESSTARTED, false);
    }

    public void setStartServiceFlag(boolean isStarted) {
        editor.putBoolean(ISSERVICESSTARTED, isStarted);
        editor.commit();
    }
//    public void setRemeber(boolean isRemembered) {
//        editor.putBoolean(ISREMEBER, isRemembered);
//        editor.commit();
//
//    }
//
//    public boolean isRemeber() {
//        return pref.getBoolean(ISREMEBER, false);
//    }
//
//    public void setLoginParameters(LoginParameters loginParamenter) {
//        SharedPreferences.Editor prefsEditor = pref.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(loginParamenter);
//        prefsEditor.putString(LOGIN_PARAMETERS, json);
//        prefsEditor.commit();
//
//    }
//
//    public LoginParameters getLoginParameters() {
//        Gson gson = new Gson();
//        String json = pref.getString(LOGIN_PARAMETERS, null);
//        LoginParameters object = gson.fromJson(json, LoginParameters.class);
//        return object;
//
//    }
//
//    public void removeSaveParameters() {
//        editor.remove(LOGIN_PARAMETERS);
//        editor.remove(ISREMEBER);
//        editor.commit();
//    }
//
//    public void savePassword(String password) {
//        editor.putString("password", password);
//        editor.commit();
//    }
//
//    public String getPassword() {
//        return pref.getString("password", null);
//    }
}
