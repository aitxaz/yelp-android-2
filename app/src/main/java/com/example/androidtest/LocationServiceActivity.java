package com.example.androidtest;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;

import com.example.androidtest.database.LocContentProvider;
import com.example.androidtest.database.LocTable;
import com.example.androidtest.database.MySharedPreference;

import java.util.Calendar;


public class LocationServiceActivity extends FragmentActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {


    private PendingIntent tracking;
    private AlarmManager alarms;
    SimpleCursorAdapter adapter;
    Button btn1, btn2, btn3;
    private long UPDATE_INTERVAL = 10000;
    private int START_DELAY = 10;
    MySharedPreference mySharedPreference;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        drawTable();
        alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        mySharedPreference = new MySharedPreference(this);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        //click listeners
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        if (mySharedPreference.isServiceStarted()) {
            btn1.setText("Stop Tracking");
        } else {
            btn1.setText("Start Tracking");

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

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.activityResumed();
//        drawTable();
    }

    @Override
    public void onPause() {
        super.onPause();
        MyApplication.activityPaused();
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

    private void showAlert(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Track Location");

        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure to want to tacked your location in the background!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mySharedPreference.setStartServiceFlag(true);
                        btn1.setText("Stop Tracking");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (!mySharedPreference.isServiceStarted()) {
                    showAlert(this);
                } else {
                    mySharedPreference.setStartServiceFlag(false);
                    btn1.setText("Start Tracking");
                    Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
                    tracking = PendingIntent.getBroadcast(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    alarms = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
                    alarms.cancel(tracking);
                }
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                Intent intent1 = new Intent(LocationServiceActivity.this, Dashboard.class);
                startActivity(intent1);
                break;
        }
    }
}