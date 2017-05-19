
package com.example.androidtest.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.androidtest.interfaces.SearchCallback;
import com.example.androidtest.web_responses.search_resturant.SearchResturantResponse;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiController {

    public final static String TAG = ApiController.class.getSimpleName();

    private static Context mContext = null;
//    private EventBus mEventBus = null;

    private static ApiController ourInstance = null;
    private final int SUCCESS_CODE = 0;
    private String ERROR_MESSAGE;


    public static ApiController getInstance(Context context) {

        mContext = context;

        if (ourInstance == null)
            ourInstance = new ApiController();

        return ourInstance;
    }


    private void postError(Throwable t) {
        String message = t.getMessage();
        if (t instanceof SocketTimeoutException
                || t instanceof ConnectException
                || t instanceof UnknownHostException
                || t instanceof SocketException) {
            message = "Internet connection is slow or unavailable.";
        }

        if (message != null && message.length() > 0) {
            if (isOnline()) {
                Toast.makeText(mContext, "Connection timed out - please try again", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

            }
//            mEventBus.post(message);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void getResturantList(double latitude, double longitude, final SearchCallback callback) {
        Call<SearchResturantResponse> call = APIHelper.getInstance().getResturantsList(latitude, longitude);
        call.enqueue(new Callback<SearchResturantResponse>() {
            @Override
            public void onResponse(Call<SearchResturantResponse> call, Response<SearchResturantResponse> response) {
                if (response != null && response.isSuccessful()) {
                    SearchResturantResponse resturantResponse = response.body();
                    callback.getResturantsList(resturantResponse);
                }
            }

            @Override
            public void onFailure(Call<SearchResturantResponse> call, Throwable t) {
                postError(t);
            }
        });
    }
}
