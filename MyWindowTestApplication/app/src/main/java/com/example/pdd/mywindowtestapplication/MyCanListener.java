package com.example.pdd.mywindowtestapplication;

import android.util.Log;

import com.hsae.hsaesdk_internal.canservice.Can_Service;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class MyCanListener implements Can_Service.Can_ServiceListener{

    @Override
    public void notifyCanSignal(int i,String s) {
        Log.d(TAG, "=====================notifyCanSignal:====================================");
        try {
            JSONObject jsonObject= new JSONObject(s);
            int value = jsonObject.getInt("SignalId"+i);
            Log.d(TAG, "notifyCanSignal: "+value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


