package com.example.pdd.mywindowtestapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by pdd on 19-11-18.
 */


public class Myboardcast extends BroadcastReceiver {

    final String testboardcast1 = "com.example.Myboardcast1";
    final String testboardcast2 = "com.example.Myboardcast2";
    final String testboardcast3 = "com.example.Myboardcast3";
    final String testboardcast4 = "com.example.Myboardcast4";
    final String getbroadcast = "android.intent.action.BOOT_COMPLETED" ;
    @Override

    public void onReceive(Context context,final Intent intent) {
        switch(intent.getAction())
        {
            case testboardcast1:
                Toast.makeText(context, "this is cast1", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "=======onReceive=====: "+testboardcast1);
                break;
            case testboardcast2:
                Toast.makeText(context, "this is cast2", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "=======onReceive=====: "+testboardcast2);
                break;
            case testboardcast3:
                Toast.makeText(context, "This is cast3", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "=======onReceive=====: "+testboardcast3);
                break;
            case testboardcast4:
                Toast.makeText(context,"This is cast4",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "=======onReceive=====: "+testboardcast4);
                break;
            case getbroadcast:
                //Toast.makeText(context,"in the frist start",Toast.LENGTH_LONG).show();
                Log.d(TAG, "=======onReceive======: "+getbroadcast);
                Intent mintent = new Intent(context,TestServer.class);
                context.startService(mintent);
                Log.d(TAG,"===Start server===");

        }
    }
}