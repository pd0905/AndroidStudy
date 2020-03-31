package com.example.pdd.mywindowtestapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hsae.hsaesdk_internal.canservice.Can_Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tianshuang.globalkey.JGlobalKeyIF;
import tianshuang.globalkey.JGlobalKeyIFEventListener;

import static android.content.ContentValues.TAG;

public class MyApplication extends Application {
    private ImageView imageView;
    public JGlobalKeyIF m_JGlobalKeyIF;
    public JGlobalKeyIFEventListener jGlobalKeyIFEventListener;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;

    Can_Service can_service = Can_Service.getInstance();
    private MyApplication myApplication;
    private static MyApplication mainApplication;
    public static MyApplication getInstance(){
        return mainApplication;
    }
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Bitmap bitmap = (Bitmap)msg.obj;
            imageView.setImageBitmap(bitmap);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication=this;
        m_JGlobalKeyIF = new JGlobalKeyIF();
        MyCanListener myCanListener = new MyCanListener();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OnNotifyCanSignal();
            }
        }).start();
        jGlobalKeyIFEventListener=new JGlobalKeyIFEventListener() {
            @Override
            public int OnFloatEvent(int type, float result) {
                return 0;
            }

            @Override
            public int OnIntEvent(int type, int result) {
                return 0;
            }
        };
        m_JGlobalKeyIF.SetListener(jGlobalKeyIFEventListener,1);
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        int displayWidth = metric.widthPixels;
        int displayHeight = metric.heightPixels;
        System.out.print("====withPixels====" + displayWidth + "====heightpixels===" + displayHeight);
        Log.d(TAG, "onCreate: " + displayWidth);
        Log.d(TAG, "onCreate: " + displayHeight);
        WindowManager.LayoutParams m_wmParamsBSDSLeft = new WindowManager.LayoutParams(960, 500, -360, 0,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.RGBA_8888);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        //view.setOnTouchListener(new FloatingOnTouchListener());
        wm.addView(view, m_wmParamsBSDSLeft);
        final EditText editText1 =  view.findViewById(R.id.IntType);
        final EditText editText2 = view.findViewById(R.id.IntValue);
        Button button1 = view.findViewById(R.id.SendIntData);

        m_JGlobalKeyIF.SetListener(new JGlobalKeyIFEventListener() {
            @Override
            public int OnFloatEvent(int type, float result) {
                Log.d(TAG, "OnFloatEvent: "+type+","+result);
                return 0;
            }

            @Override
            public int OnIntEvent(int type, int result) {
                Log.d(TAG, "OnIntEvent: "+type+","+result);
                return 0;
            }
        },1);

        button1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                int value1=0,type1=1;
                if(!TextUtils.isEmpty(editText1.getText().toString())){
                    type1=Integer.parseInt(editText1.getText().toString());
                }
                if(!TextUtils.isEmpty(editText2.getText().toString())){
                    value1=Integer.parseInt(editText2.getText().toString());
                }
                m_JGlobalKeyIF.SetIntData(type1,value1);
            }
        });
        final EditText editText3 = view.findViewById(R.id.FloatType);
        final EditText editText4 = view.findViewById(R.id.FloatValue);
        Button button2 = view.findViewById(R.id.SendFloatData);
        button2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText().show();
                int type2=0;
                float value2=0;
                if(!TextUtils.isEmpty(editText3.getText().toString())){
                    type2 =Integer.parseInt(editText3.getText().toString());
                }
                if(!TextUtils.isEmpty(editText4.getText().toString())){
                    value2 = Float.parseFloat(editText4.getText().toString());
                }
                m_JGlobalKeyIF.SetFloatData(type2,value2);
            }
        });
        Button button3 = view.findViewById(R.id.GetRNG);
        final EditText editText5 = view.findViewById(R.id.ValueRNG);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetCanValue();
                    }
                }).start();
            }
        });
        Button button10 = view.findViewById(R.id.button10);
        button10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JGlobalKeyIF.HS591Config hs591Config = new JGlobalKeyIF.HS591Config();
                hs591Config.m_bIfAdvanceDynamicTrace = true;
                hs591Config.m_bIfAdvanceStaticTrace = true;
                hs591Config.m_bIfDrivingDynamicTrace = true;
                hs591Config.m_bIfDrivingStaticTrace = true;
                hs591Config.m_bIfLeftSteerStartGlobal = true;
                hs591Config.m_bIfRadarStartGlobal = true;
                hs591Config.m_bIfRightSteerStartGlobal = true;
                hs591Config.m_bIfWheelSteeringStartGlobal = true;

                m_JGlobalKeyIF.SetHS591Config(hs591Config);
            }
        });

        Button button11 = view.findViewById(R.id.button11);
        button11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JGlobalKeyIF.HS532Config hs532Config = new JGlobalKeyIF.HS532Config();
                hs532Config.m_bIfMODS = true;
                hs532Config.m_bIfRadarStartGlobal = true;
                hs532Config.m_bIfWheelSteeringStartGlobal = true;
                hs532Config.m_bIfSteerStartGlobal = true;
                //hs532Config.m_iMODSSpeed = true;

                m_JGlobalKeyIF.SetHS532Config(hs532Config);
            }
        });
        
        Button button12 = view.findViewById(R.id.button12);
        button12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                m_JGlobalKeyIF.SetIntData(32,0);
                Log.d(TAG, "onClick: button entern to 360");
            }
        });




        Button button4 = view.findViewById(R.id.GetSpeed);
        final EditText editText6  = view.findViewById(R.id.ValueSpeed);
        button4.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetCanServerValue();
                    }
                }).start();
            }
        });
        imageView = view.findViewById(R.id.image);
        Button button5 = view.findViewById(R.id.getPicture);
        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient Client = new OkHttpClient();
                            RequestBody requestBody = new FormBody.Builder()
                                    .add("appKey","ee8b1619d54c3643fd245ae40938bf07")
                                    .add("appId","iov2049586751b9bc6bd9631abe7a021146")
                                    .add("timestamp","1499159290")
                                    .add("api","ly.dvr.img.query")
                                    .add("sign","a869173746864fc4163b02530745130e")
                                    .add("vinCode","DNTC2019120833")
                                    .add("uploadType","4")
                                    .add("pageIndex","1")
                                    .add("pageSize","101")
                                    .build();
                            Request request = new Request.Builder()
                                    .url("https://vitappkf.venucia.com/iov_gw/gw")
                                    .post(requestBody)
                                    .build();
                            Response response = Client.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.d(TAG, "getImageFile: "+responseData);
                            JSONObject ret_json = new JSONObject(responseData);

                            String imgurl = ret_json.getString("rows");
                            Log.d(TAG, "===getImageFile: ==="+imgurl);
                            JSONArray jsonArray = new JSONArray(imgurl);
                            for(int i = 0;i<jsonArray.length();++i)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String imgUrl = jsonObject.getString("imgUrl");
                                Log.d(TAG, "getImageFile: " + imgUrl);
                                Request request1 = new Request.Builder()
                                        .url(imgUrl)
                                        .build();
                                Response response1 = Client.newCall(request1).execute();
                                InputStream inputStream =response1.body().byteStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                Message msg = new Message();
                                msg.obj = bitmap;
                                handler.sendMessage(msg);
                                Log.d(TAG, "getImageFile: "+ bitmap );
                                if(!imgUrl.isEmpty())
                                    break;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        });
    }


    public void GetCanValue(){

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SignalId", 130);
            String str = can_service.getCanSignalByValue(jsonObject.toString());
            Log.d(TAG, "================GetCanValue:=================== "+str);
            JSONObject ret_json = new JSONObject(str);
            int ret = ret_json.getInt("SignalName130");
            Log.d(TAG, "================GetCanValue:=================== "+ret);
        }catch(JSONException e){
            e.printStackTrace();
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
    public void GetCanServerValue(){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SignalId",28);
            String str1 = can_service.getCanSignalByValue(jsonObject.toString());
            Log.d(TAG, "==================GetCanServerValue:=============== "+str1);
            JSONObject ret_json1 = new JSONObject(str1);
            double ret1 = ret_json1.getDouble("SignalName28");
            Log.d(TAG, "==================GetCanServerValue:=============== "+ret1);
        }catch(JSONException e){
            e.printStackTrace();

        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
    public void OnNotifyCanSignal(){
        try {
            MyCanListener myCanListener = new MyCanListener();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("SignalId", 28);
            myCanListener.notifyCanSignal(28, jsonObject.toString());
        }catch(JSONException e){
            e.printStackTrace();
        }

    }
  /*@Override
  public void onCreate() {
      super.onCreate();
      DisplayMetrics metric = new DisplayMetrics();
      WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
      wm.getDefaultDisplay().getMetrics(metric);
      int displayWidth = metric.widthPixels;
      int displayHeight = metric.heightPixels;
      System.out.print("====withPixels===="+displayWidth+"====heightpixels==="+displayHeight);

      Log.d( TAG,"onCreate: "+displayWidth);
      Log.d(TAG, "onCreate: "+displayHeight);

      WindowManager.LayoutParams m_wmParamsBSDSLeft = new WindowManager.LayoutParams(1920, 720, 0, -100,
              WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
              WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
              PixelFormat.RGBA_8888);
      View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
      wm.addView(view, m_wmParamsBSDSLeft);
  }
  private class FloatingOnTouchListener implements View.OnTouchListener {
      private int x;
      private int y;

      @Override
      public boolean onTouch(View view, MotionEvent event) {
          switch (event.getAction()) {
              case MotionEvent.ACTION_DOWN:
                  x = (int) event.getRawX();
                  y = (int) event.getRawY();
                  break;
              case MotionEvent.ACTION_MOVE:
                  int nowX = (int) event.getRawX();
                  int nowY = (int) event.getRawY();
                  int movedX = nowX - x;
                  int movedY = nowY - y;
                  x = nowX;
                  y = nowY;
                  layoutParams.x = layoutParams.x + movedX;
                  layoutParams.y = layoutParams.y + movedY;
                  windowManager.updateViewLayout(view, layoutParams);
                  break;
              default:
                  break;
          }
          return false;
      }
  }*/
}
