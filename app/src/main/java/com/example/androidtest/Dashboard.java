package com.example.androidtest;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidtest.api.ApiController;
import com.example.androidtest.database.LocContentProvider;
import com.example.androidtest.database.LocTable;
import com.example.androidtest.interfaces.SearchCallback;
import com.example.androidtest.items.Globals;
import com.example.androidtest.web_responses.search_resturant.Business;
import com.example.androidtest.web_responses.search_resturant.SearchResturantResponse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import fr.quentinklein.slt.LocationTracker;

public class Dashboard extends FragmentActivity implements OnMapReadyCallback, SearchCallback, LoaderManager.LoaderCallbacks<Cursor> {

    private GoogleMap mMap;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    ApiController apiController;
    ArrayList<Business> markersArray;
    private HashMap<Marker, Business> mMarkersHashMap;
    private ProgressBar progressBar;
    LocationTracker tracker;

    private PendingIntent tracking;
    private AlarmManager alarms;
    SimpleCursorAdapter adapter;
    private long UPDATE_INTERVAL = 1000;
    private int START_DELAY = 1000;

    private double currentLat = 0.0, currentLng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        progressBar.setVisibility(View.VISIBLE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        apiController = ApiController.getInstance(this);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(ConfigNotify.PUSH_NOTIFICATION)) {

//                    updateMap(intent.getDoubleExtra("latitude", 0.0), intent.getDoubleExtra("longitude", 0.0));
                    updateResturnat(intent.getDoubleExtra("latitude", 0.0), intent.getDoubleExtra("longitude", 0.0));
                }
            }
        };
        showAlert(this);
    }

    private void showAlert(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Yelp");

        // set dialog message
        alertDialogBuilder
                .setMessage("Allow phone to track your location in the background?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        setRecurringAlarm(getBaseContext());

                        // if this button is clicked, close
                        // current activity

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void updateResturnat(double latitude, double longitude) {
        apiController.getResturantList(latitude, longitude, this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private void updateMap(double latitude, double longitude) {
        mMap.clear();
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(sydney, 18);
        mMap.animateCamera(yourLocation);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GPSTracker gpsTracker = new GPSTracker(this);
        //Current Lat lngs
        currentLat = gpsTracker.getLatitude();
        currentLng = gpsTracker.getLongitude();

        // USA lat lngs,
//        currentLat = 37.0902;
//        currentLng = 95.7129;

        //api defined lat lngs
//        currentLat = 37.786882;
//        currentLng = -122.399972;
        if (Globals.isOnline(this)) {
//            updateResturnat(37.786882, -122.399972);
            updateResturnat(currentLat, currentLng);
        }

//        LatLng sydney = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
//        mMap.addMarker(new MarkerOptions().position(sydney));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(sydney, 18);
////            mMap.setMyLocationEnabled(false);
////            map.getUiSettings().setMyLocationButtonEnabled(false);
//        mMap.animateCamera(yourLocation);

    }

    @Override
    public void onResume() {
        MyApplication.activityResumed();
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ConfigNotify.PUSH_NOTIFICATION));


//        drawTable();
    }

    @Override
    public void onPause() {
        MyApplication.activityPaused();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();

    }

    @Override
    public void getResturantsList(SearchResturantResponse resturantResponse) {
        progressBar.setVisibility(View.GONE);
        mMap.clear();
        markersArray = new ArrayList<>();
        mMarkersHashMap = new HashMap<>();
        markersArray = resturantResponse.getBusinesses();
        if (markersArray != null && markersArray.size() > 0) {
            plotMarkers(markersArray);

        } else {
            updateMap(currentLat, currentLng);

        }
    }

    private void plotMarkers(final ArrayList<Business> markers) {
        if (markers.size() > 0) {
            ArrayList<LatLng> latLngs = new ArrayList<>();
            for (Business myMarker : markers) {
                LatLng latLng;
                if (mMap == null) {
                    return;
                }
                latLng = new LatLng(myMarker.getCoordinates().getLatitude(), myMarker.getCoordinates().getLongitude());
                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(latLng);
                latLngs.add(latLng);
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_32));
                markerOption.snippet(myMarker.getId());
                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);
                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String markerId = marker.getSnippet();
//                        int markerID = Integer.parseInt(marker.getSnippet());
                        for (int i = 0; i < markers.size(); i++) {
//                            Log.d("", "markerId: " + markerId + " affiliateId: " + markers.get(i).getAffil_id());
                            if (markerId == null) {
                                return;
                            } else if (markerId.equals(markers.get(i).getId())) {

                                Business business = markers.get(i);
                                Intent intent = new Intent(Dashboard.this, ResturantDetails.class);
                                intent.putParcelableArrayListExtra(Globals.CATEGORIES, (ArrayList<? extends Parcelable>) business.getCategories());
                                intent.putExtra(Globals.RATING, String.valueOf(business.getRating()));
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(Globals.RESTURANT, business);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                break;
                            }
                        }
                    }
                });
            }
            //
            if (latLngs != null && !latLngs.isEmpty()) {
                reAnimateCamera(latLngs);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {LocTable.COLUMN_ID, LocTable.COLUMN_TIME,
                LocTable.COLUMN_LONGITUDE, LocTable.COLUMN_LATITUDE};
        CursorLoader cursorLoader = new CursorLoader(this,
                LocContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        adapter.swapCursor(null);
    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        public MarkerInfoWindowAdapter() {
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v = getLayoutInflater().inflate(R.layout.custom_marker, null);

            Business myMarker = mMarkersHashMap.get(marker);

//                    ImageView markerIcon = (ImageView) v.findViewById(R.id.mark);
            RelativeLayout parent = (RelativeLayout) v.findViewById(R.id.parent);
            TextView resturantName = (TextView) v.findViewById(R.id.resturantName);
            TextView ratingResturant = (TextView) v.findViewById(R.id.ratingResturant);
            TextView addressResturant = (TextView) v.findViewById(R.id.addressResturant);

//                    markerIcon.setImageResource(manageMarkerIcon(myMarker.getmIcon()));
            try {
                if (resturantName != null) {
                    resturantName.setText(myMarker.getName());
                    ratingResturant.setText("Rating: " + myMarker.getRating());
                    addressResturant.setText(myMarker.getLocation().getDisplayAddress().get(0) + ", " + myMarker.getLocation().getDisplayAddress().get(1));

                } else {
                    parent.setVisibility(View.GONE);

                }

            } catch (Exception e) {
//                Toast.makeText(delegate, "exception : " + e.toString(), Toast.LENGTH_SHORT).show();
            }


            return v;
        }
    }

    private void reAnimateCamera(
            ArrayList<LatLng> coordinate_array2) {
        try {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            LatLng latLng;
            Double d_lat, d_lng;
            for (LatLng marker : coordinate_array2) {
                d_lat = marker.latitude;
                d_lng = marker.longitude;
                latLng = new LatLng(d_lat, d_lng);
                builder.include(latLng);
            }
            LatLngBounds bounds = builder.build();

            // Then obtain a movement description object by using the factory:
//            CameraUpdateFactory:

            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,
                    0);
//            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
            LatLng current = new LatLng(currentLat, currentLng);
//            String nameLoc = getAddress(currentLat, currentLng);
//            mMap.addMarker(new MarkerOptions().position(current).title(nameLoc));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));

//            CameraUpdate center =
//                    CameraUpdateFactory.newLatLng(new LatLng(currentLat,
//                            currentLng));
//            CameraUpdate zoom = CameraUpdateFactory.zoomTo(5);
//
//            Log.d("", "currentLat: " + currentLat + " currentLng: " + currentLng);
            LatLng coordinate = new LatLng(currentLat,
                    currentLng);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
//            mMap.setMyLocationEnabled(false);
//            map.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.animateCamera(yourLocation);

        } catch (Exception e) {
        }
    }

    private void setRecurringAlarm(Context context) {

        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add 5 minutes to the calendar object
        cal.add(Calendar.SECOND, START_DELAY);

        Intent intent = new Intent(context, AlarmReceiver.class);

        tracking = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), UPDATE_INTERVAL, tracking);
    }
}

