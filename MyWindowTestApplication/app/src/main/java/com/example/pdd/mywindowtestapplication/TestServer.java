package com.example.pdd.mywindowtestapplication;

import android.app.Service;

import android.content.Intent;

import android.os.IBinder;

import android.util.Log;


import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.*;

/**
 * Created by pdd on 19-11-19.
 */

public class TestServer extends Service {

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "=======start Server onCreate====");
    }
    public IBinder onBind(Intent intent){
         Log.d("start server", "start server");
         Toast.makeText(getApplicationContext(),"++++++++++++",LENGTH_LONG).show();
         return null;
    }

}
