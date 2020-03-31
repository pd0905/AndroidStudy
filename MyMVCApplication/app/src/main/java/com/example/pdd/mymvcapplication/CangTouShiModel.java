package com.example.pdd.mymvcapplication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class CangTouShiModel implements ICangTouShi{
    private String str = "mvc-application" ;
    @Override
    public void doRequest(String num, String type, String yayuntype, String key, final Bean<CangTouShiBean> back) {

        //请求数据
        //使用OkHttp

        OkHttpClient client = new OkHttpClient();

       /* RequestBody body = new FormBody.Builder()
                .add("showapi_appid","27306")
                .add("showapi_sign","150e9206e7f542bab4affe49d73cb920")
                .add("num",num)
                .add("type",type)
                .add("yayuntype",yayuntype)
                .add("key",key).build();      */
         RequestBody Requtestbody =  new MultipartBody.Builder()
                 .addFormDataPart("showapi_appid","27306")
                 .addFormDataPart("showapi_sign","150e9206e7f542bab4affe49d73cb920")
                 .addFormDataPart("num",num)
                 .addFormDataPart("type",type)
                 .addFormDataPart("yayuntype",yayuntype)
                 .addFormDataPart("key",key).build();
        Log.d(TAG, "doRequest: "+Requtestbody.toString());
        Request request = new Request.Builder()
                .post(Requtestbody)
                .url("http://route.showapi.com/950-1").build();

        Log.d(TAG, "doRequest: "+request.toString());
        Call call = client.newCall(request);
        //异步请求，子线程
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG","-----------"+e.getMessage());
                back.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, "onResponse: "+json);
                Gson gson = new Gson();
                CangTouShiBean bean = gson.fromJson(json, CangTouShiBean.class);
                back.onSuccess(bean);
            }
        });

    }


}
